package org.shop.controller;

import org.shop.ErrorResultConvertor;
import org.shop.Result;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.shop.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@RequestMapping("/register")
	public Result<CustomerVO> register(@Validated(CustomerVO.RegistryGroup.class)  CustomerVO vo, BindingResult result){
		if (result.hasErrors()){
				return Result.validationError(result);
		}
		return Result.of(userServiceImpl.register(vo).orElse(null));
	}

	@RequestMapping("/login")
	public Result<CustomerVO> login(@RequestBody @Validated(CustomerVO.LoginGroup.class)  CustomerVO vo, BindingResult result){
		if (result.hasErrors()){
				return Result.validationError(result);
		}
		return userServiceImpl.login(vo).map(Result::of).orElse(Result.badRequest("用户名或密码错误"));
	}

}
