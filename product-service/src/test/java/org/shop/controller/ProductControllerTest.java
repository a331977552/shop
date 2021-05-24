package org.shop.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.util.Page;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.shop.test.utils.TestHttpClient.strResRef;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

	@LocalServerPort
	private int port;


	@Autowired
	private RestTestHelper helper;
	ParameterizedTypeReference<Result<ProductReturnVO>> resProReturnRef = new ParameterizedTypeReference<Result<ProductReturnVO>>() {};
	ParameterizedTypeReference<String> strRef = new ParameterizedTypeReference<String>() {};
	ParameterizedTypeReference<Result<String>> strResultRef = new ParameterizedTypeReference<Result<String>>() {};
	ParameterizedTypeReference<Result<Page<ProductReturnVO>>> resPageProReturnRef = new ParameterizedTypeReference<Result<Page<ProductReturnVO>>>() {};


	@BeforeEach
	void setUp() {
		helper.setPort(port);
		helper.setUIPath(getUiPath());
	}

	@AfterEach
	void tearDown() {
	}

	private String getUiPath(){
		return "api/product";
	}
	@Test
	void addProduct() {

		final String token = getToken();

		testEmptyContent(token);

		testInvalidBody(token);

		ResponseEntity<Result<ProductReturnVO>> successRes = testSuccessBody(token);

		delete(token, successRes);
	}

	private String getToken() {
		final ResponseEntity<Result<String>> post = helper.loginWithDefault();
		assertEquals(200, post.getBody().getCode());
		assertEquals(true, post.getBody().getResult()!=null);
		final String token = post.getBody().getResult();
		return token;
	}

	private void delete(String token, ResponseEntity<Result<ProductReturnVO>> successRes) {
		final ResponseEntity<Result<String>> delete = helper.builder().withToken(token).setUipath("api/product/" + successRes.getBody().getResult().getId()).build()
				.delete(strResRef, null);
		System.out.println(delete);
		assertEquals(200,delete.getStatusCodeValue());
	}

	private ResponseEntity<Result<ProductReturnVO>> testSuccessBody(String token) {
		final ProductAddVO successVO = new ProductAddVO();
		successVO.setName("测试产品 new");
		successVO.setCategory(1);
		List<ProductAddVO.SkuAddVO> list= new ArrayList<>();
		ProductAddVO.SkuAddVO vo1 =new ProductAddVO.SkuAddVO();
		vo1.setAttribute("");
		vo1.setStock(99);
		vo1.setPrice(new BigDecimal("39.99"));
		vo1.setImg("60ab262d928e137a2e1228b0");
		list.add(vo1);
		successVO.setSkuList(list);
		successVO.setStandardImg("60ab262d928e137a2e1228b0");
		successVO.setThumbnailImg("60ab262e928e137a2e1228b2");
		ResponseEntity<Result<ProductReturnVO>> successRes = helper.builder().withToken(token).build().post(successVO,resProReturnRef);
		System.out.println(successRes);

		assertEquals(200,successRes.getStatusCodeValue());
		assertEquals(true,successRes.getBody().getResult().getId().length() == 32);
		return successRes;
	}

	private void testInvalidBody(String token) {
		final ProductAddVO productAddVO = new ProductAddVO();
		productAddVO.setName("测试产品");

		ResponseEntity<Result<ProductReturnVO>> invalidRes = helper.builder().withToken(token).build().post(productAddVO,resProReturnRef);
		assertEquals(400,invalidRes.getStatusCodeValue());
		assertEquals("必须指定产品目录",invalidRes.getBody().getMsgDetail());
	}

	private void testEmptyContent(String token){
		ResponseEntity<Result<String>> emptyRes = helper.setUIPath(getUiPath()).setPort(port).
				builder().withToken(token).build().post(new ProductAddVO(),strResultRef);
		System.out.println(emptyRes);
		assertEquals(400,emptyRes.getStatusCodeValue());
		assertEquals("请求异常",emptyRes.getBody().getMsg());

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