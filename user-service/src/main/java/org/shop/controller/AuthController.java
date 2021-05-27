package org.shop.controller;


import lombok.extern.log4j.Log4j2;
import org.shop.Constants;
import org.shop.common.RedisService;
import org.shop.common.Result;
import org.shop.model.vo.CustomerVO;
import org.shop.common.security.AuthenticationEntity;
import org.shop.service.UserService;
import org.shop.common.util.BeanConvertor;
import org.shop.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
			log.debug("error vo: {}",vo);
			return new ResponseEntity(Result.validationError(result), HttpStatus.BAD_REQUEST);
		}

		Optional<CustomerVO> customerOpt = userService.login(vo);
		if (customerOpt.isEmpty()) {
			redisService.increment(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 1);
			redisService.expire(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip, 60);
			throw new BadCredentialsException("用户名或密码错误");
		} else {
			redisService.delete(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip);
		}
		final CustomerVO customerVO = customerOpt.get();
		final String token = jwtTokenUtil.generateToken(customerVO.getUsername());
		final long expiration = jwtTokenUtil.getExpirationLength();
		final AuthenticationEntity authenticationEntity = BeanConvertor.convert(customerVO, AuthenticationEntity.class);
		redisService.set(customerVO.getUsername(),authenticationEntity,expiration);
		return new ResponseEntity(Result.of(token), HttpStatus.OK);
	}

	private String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}


}
