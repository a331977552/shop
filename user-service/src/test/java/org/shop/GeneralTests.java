package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class GeneralTests {

	@Test
	void testOptional(){

		Optional<CustomerDAO> tes= Optional.of(new CustomerDAO());
		Optional<CustomerVO> customerVO = tes.map(customerDAO -> null);
		System.out.println(customerVO.isPresent());
	}

	@Test
	void test(){

		Optional<CustomerDAO> tes= Optional.of(new CustomerDAO());
		Optional<CustomerVO> customerVO = tes.map(customerDAO -> null);
		System.out.println(customerVO.isPresent());
	}
	@Test
	void passwordEncoder(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encode = bCryptPasswordEncoder.encode("1234");
		String encode1 = bCryptPasswordEncoder.encode("1234");
		boolean matches = bCryptPasswordEncoder.matches("1234", encode);
		System.out.println(encode);
		System.out.println(encode1);
		System.out.println(matches);
		System.out.println(encode.equals(encode1));
	}

}
