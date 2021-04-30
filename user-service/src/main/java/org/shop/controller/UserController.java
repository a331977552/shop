package org.shop.controller;

import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@RequestMapping("/register")
	public CustomerVO register(@Validated  CustomerVO vo, BindingResult result){
		Optional<CustomerVO> register = userServiceImpl.register(vo);
		return register.orElse(new CustomerVO());
	}
}
