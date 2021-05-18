package org.shop.controller;

import org.shop.ErrorResultConvertor;
import org.shop.Result;
import org.shop.exception.RegistrationException;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/signup")
	public Result<CustomerVO> register(@RequestBody @Validated(CustomerVO.RegistryGroup.class) CustomerVO vo, BindingResult result) {
		if (result.hasErrors()) {
			throw new RegistrationException(ErrorResultConvertor.getErrorMsg(result));
		}
		return Result.of(userService.register(vo).orElse(null));
	}


	@RequestMapping("/getInfo")
	public Result<CustomerVO> login() {
		CustomerVO vo = new CustomerVO();
		vo.setUsername("test");
		return Result.of(vo);
	}

	@GetMapping()
	public Result<CustomerVO> get(@AuthenticationPrincipal String username) {
		Optional<CustomerVO> userById = userService.findByUserName(username);
		CustomerVO customerVO = userById.get();
		customerVO.setPassword("");
		return Result.of(customerVO);
	}


}
