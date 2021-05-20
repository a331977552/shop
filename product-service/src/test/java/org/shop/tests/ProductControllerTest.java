package org.shop.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.security.AuthenticationEntity;
import org.shop.common.util.Page;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.tests.util.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

	@LocalServerPort
	private int port;


	@Autowired
	private RestTestHelper helper;
	ParameterizedTypeReference resProReturnRef = new ParameterizedTypeReference<Result<ProductReturnVO>>() {};
	ParameterizedTypeReference strRef = new ParameterizedTypeReference<String>() {};
	ParameterizedTypeReference strResultRef = new ParameterizedTypeReference<Result<String>>() {};
	ParameterizedTypeReference resPageProReturnRef = new ParameterizedTypeReference<Result<Page<ProductReturnVO>>>() {};


	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void addProduct() {

		final ResponseEntity<Result<String>> post = helper.builder().setPort(81).setUipath("/user/authenticate").build().post(new AuthenticationEntity("a123456","123456"), strResultRef);
		assertEquals(200, post.getBody().getCode());
		assertEquals(true, post.getBody().getResult()!=null);

		final ProductAddVO productAddVO = new ProductAddVO();
		ResponseEntity<Result<ProductReturnVO>> emptyRes = helper.builder().build().post(productAddVO,resProReturnRef);
		ResponseEntity<Result<ProductReturnVO>> invalidRes = helper.builder().build().post(productAddVO,resProReturnRef);
		ResponseEntity<Result<ProductReturnVO>> successRes = helper.builder().build().post(productAddVO,resProReturnRef);

	}

	@Test
	void updateProduct() {
	}

	@Test
	void deleteProduct() {
	}

	@Test
	void getProduct() {
	}

	@Test
	void getAllProductByPage() {
	}

	@Test
	void getAllProduct() {
	}
}