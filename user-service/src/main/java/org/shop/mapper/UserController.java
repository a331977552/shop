package org.shop.mapper;

import org.shop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


	@RequestMapping("/register")
	public Customer register(){
		return new Customer();
	}
}
