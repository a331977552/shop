package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.model.dao.CustomerDAO;
import org.shop.model.vo.CustomerVO;

import java.util.Optional;

public class GeneralTests {

	@Test
	void testOptional(){

		Optional<CustomerDAO> tes= Optional.of(new CustomerDAO());
		Optional<CustomerVO> customerVO = tes.map(customerDAO -> null);
		System.out.println(customerVO.isPresent());
	}
}
