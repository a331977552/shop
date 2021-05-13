package org.shop.controller;

import org.shop.ErrorResultConvertor;
import org.shop.Result;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@RequestMapping("/signup")
	public Result<CustomerVO> register(@RequestBody @Validated(CustomerVO.RegistryGroup.class) CustomerVO vo, BindingResult result) {
		if (result.hasErrors()) {
			return Result.validationError(result);
		}
		return Result.of(userServiceImpl.register(vo).orElse(null));
	}


	@RequestMapping("/getInfo")
	public Result<CustomerVO> login() {
		CustomerVO vo = new CustomerVO();
		vo.setUsername("test");
		return Result.of(vo);
	}

}
