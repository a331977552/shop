package org.shop.controller;


import lombok.extern.log4j.Log4j2;
import org.shop.Constants;
import org.shop.RedisService;
import org.shop.Result;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.shop.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController()
@Log4j2
@RequestMapping(value = "/user")
public class AuthController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Value( "${jwt.expiration.time}" )
	private  int JWT_EXPIRATION_TIME ;
	private final int MAXIMUM_ATTEMPT = 10;

	@RequestMapping(value = "/authenticate")
	public ResponseEntity<Result<String>> login(@RequestBody @Validated(CustomerVO.LoginGroup.class) CustomerVO vo, BindingResult result, HttpServletRequest request) throws Exception {
		String ip = getClientIP(request);
		Object obj = redisService.get(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip);
		log.debug("ip address {}, times:{}", ip, obj);
		if (obj != null && ((int) obj) > MAXIMUM_ATTEMPT) {
			return new ResponseEntity(Result.validationError("请求太过频繁"), HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			redisService.increment(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 1);
			redisService.expire(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 60);
			return new ResponseEntity(Result.validationError(result), HttpStatus.BAD_REQUEST);
		}

		Optional<CustomerVO> login = userService.login(vo);
		if (login.isEmpty()) {
			redisService.increment(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 1);
			redisService.expire(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 60);
			throw new BadCredentialsException("用户名或密码错误");
		} else {
			redisService.delete(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip);
		}
		redisService.set(login.get().getUsername(), login.get(),JWT_EXPIRATION_TIME);
		return new ResponseEntity(Result.of(jwtTokenUtil.generateToken(login.get().getUsername())), HttpStatus.OK);
	}

	private String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}


}
