package org.shop.controller;


import org.shop.Constants;
import org.shop.RedisService;
import org.shop.Result;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.shop.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/user",consumes = {})
public class RegistryController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	private final int MAXIMUM_ATTEMPT = 10;

	@RequestMapping(value = "/authenticate",consumes = {})
	public Result<String> login(@RequestBody @Validated(CustomerVO.LoginGroup.class) CustomerVO vo, BindingResult result, HttpServletRequest request) throws Exception {
		if (result.hasErrors()) {
			return Result.validationError(result);
		}
		String ip = getClientIP(request);
		Object obj =  redisService.get(Constants.REDIS_USER_LOGIN_ATTEMPT + "_" + ip);
		if (obj !=null && ((int)obj) >MAXIMUM_ATTEMPT) {
			return Result.unknownError("请求太过频繁");
		}
		Optional<CustomerVO> login = userService.login(vo);
		if(login.isEmpty())
			throw new BadCredentialsException("用户名或密码错误");
		String s = jwtTokenUtil.generateToken(login.get().getId());
		return Result.of(s);
	}
	private String getClientIP(HttpServletRequest request) {
		String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null){
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}





}
