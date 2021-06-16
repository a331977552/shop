package org.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.model.vo.ProductAddVO;
import org.shop.model.vo.ProductReturnVO;
import org.shop.model.vo.ProductUpdateVO;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

		Map<String,String> map =new HashMap<>();
		map.put("颜色", "紫色");
		map.put("尺寸", "40X40");

		try {
			vo1.setAttribute(new ObjectMapper().writeValueAsString(map));
			System.out.println(vo1.getAttribute());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
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
		assertEquals("商品 sku不能为空,必须指定产品目录" ,invalidRes.getBody().getMsgDetail());
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
		final String token = getToken();
		final ProductReturnVO product = getProduct(token, "853ff0be46ef4f9a9c3c29bb11a6a389");
		ProductUpdateVO unchanged = new ProductUpdateVO();
		ProductUpdateVO updateVO = new ProductUpdateVO();
		unchanged.setSkuList(BeanConvertor.convert(product.getSkuList(), ProductUpdateVO.SkuUpdateVO.class));
		updateVO.setSkuList(BeanConvertor.convert(product.getSkuList(), ProductUpdateVO.SkuUpdateVO.class));
		BeanUtils.copyProperties(product,unchanged);
		BeanUtils.copyProperties(product,updateVO);
		updateVO.setName("test update");
		updateVO.setStatus("OUT_OF_ORDER2");
		System.out.println(updateVO);
		final ResponseEntity<String> put = helper.builder().setPort(port).setUipath(getUiPath()).withToken(token)
				.build().put(null, updateVO, strRef);
		System.out.println(put);
		assertEquals(400, put.getStatusCodeValue());
		updateVO.setStatus("OUT_OF_ORDER");
		final ResponseEntity<String> put2 = helper.builder().setPort(port).setUipath(getUiPath()).withToken(token)
				.build().put(null, updateVO, strRef);
		System.out.println(put2);
		assertEquals(400, put2.getStatusCodeValue());

		final ResponseEntity<String> success = helper.builder().setPort(port).setUipath(getUiPath()).withToken(token)
				.build().put(null, updateVO, strRef);
		System.out.println(success);
		assertEquals(200, success.getStatusCodeValue());

		final ResponseEntity<String> recover = helper.builder().setPort(port).setUipath(getUiPath()).withToken(token)
				.build().put(null, unchanged, strRef);

		assertEquals(200, recover.getStatusCodeValue());

	}
	@Test
	void testGetProduct() {
		final String token = getToken();
		String productid = "853ff0be46ef4f9a9c3c29bb11a6a389";
		getProduct(token, productid);
	}

	ProductReturnVO getProduct(String token,String productId){
		final ResponseEntity<Result<ProductReturnVO>> resultResponseEntity = helper.builder().setPort(port).setUipath(getUiPath() +"/"+productId ).withToken(token)
				.build().get(null, resProReturnRef, null);
		final int statusCodeValue = resultResponseEntity.getStatusCodeValue();

		System.out.println(resultResponseEntity);
		final ProductReturnVO result = resultResponseEntity.getBody().getResult();
		assertEquals(200, statusCodeValue);
		assertEquals(32, result.getId().length());
		assertTrue(result.getSkuList().get(0).getAttribute() !=null);
		return result;
	}
	@Test
	void getAllProductByPage() {
		final String token = getToken();

		final ResponseEntity<Result<Page<ProductReturnVO>>> responseEntity = helper.builder().setPort(port).setUipath(getUiPath()+"/0/11").withToken(token)
				.build().get(null,resPageProReturnRef,null);
		System.out.println(responseEntity);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(11, responseEntity.getBody().getResult().getItems().size());

		final ResponseEntity<Result<Page<ProductReturnVO>>> responseEntity2 = helper.builder().setPort(port).setUipath(getUiPath()+"/1/10").withToken(token)
				.build().get(null,resPageProReturnRef,null);
		final String id = responseEntity.getBody().getResult().getItems().get(10).getId();
		final String id2 = responseEntity2.getBody().getResult().getItems().get(0).getId();
		assertEquals(id, id2);
	}

	@Test
	void findByCategoryIdWithPage() {

		final String token = getToken();

		final ResponseEntity<Result<Page<ProductReturnVO>>> responseEntity = helper.builder().setPort(port).setUipath(getUiPath()+"/findByCategoryId/1/0/11").withToken(token)
				.build().get(null,resPageProReturnRef,null);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(11, responseEntity.getBody().getResult().getItems().size());

		final ResponseEntity<Result<Page<ProductReturnVO>>> responseEntity2 = helper.builder().setPort(port).setUipath(getUiPath()+"/findByCategoryId/1/1/10").withToken(token)
				.build().get(null,resPageProReturnRef,null);
		final String id = responseEntity.getBody().getResult().getItems().get(10).getId();
		final String id2 = responseEntity2.getBody().getResult().getItems().get(0).getId();
		final Page<ProductReturnVO> page1 = responseEntity.getBody().getResult();
		final Page<ProductReturnVO> page2 = responseEntity2.getBody().getResult();
		for (ProductReturnVO item : page1.getItems()) {
			assertEquals(1, item.getCategory());
			System.out.println(item);
		}
		System.out.println();
		for (ProductReturnVO item : page2.getItems()) {
			assertEquals(1, item.getCategory());
			System.out.println(item);
		}
		assertEquals(id, id2);

	}
}