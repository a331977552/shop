package org.shop.controller;

import org.shop.common.util.ErrorResultConvertor;
import org.shop.common.Result;
import org.shop.exception.RegistrationException;
import org.shop.exception.UserUpdateException;
import org.shop.model.vo.CustomerVO;
import org.shop.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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



	@GetMapping()
	public Result<CustomerVO> get(@AuthenticationPrincipal String username) {
		Optional<CustomerVO> userById = userService.findByUserName(username);
		CustomerVO customerVO = userById.get();
		customerVO.setPassword(null);
		return Result.of(customerVO);
	}

	@PutMapping()
	public Result<CustomerVO> update(@AuthenticationPrincipal String username,@Validated(CustomerVO.UpdateGroup.class) @RequestBody  CustomerVO body, BindingResult result) {
		if (result.hasErrors()) {
			throw new UserUpdateException(ErrorResultConvertor.getErrorMsg(result)) ;
		}
		String username1 = body.getUsername();
		if(!username.equals(username1)){
			throw new UserUpdateException("you can only update your own info");
		}
		userService.updateInfo(body);
		return Result.success(null);
	}



}
