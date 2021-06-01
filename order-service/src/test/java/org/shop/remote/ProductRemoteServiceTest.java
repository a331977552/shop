package org.shop.remote;

import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.dto.ProductReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class ProductRemoteServiceTest {

	@Autowired
	ProductServiceProxy service;
	@Test
	void getProduct(){

		final Result<ProductReturnDTO> product = service.getProductByID("000f17368bc54869af74feed991be3d5");
		System.out.println(product);
	}

}