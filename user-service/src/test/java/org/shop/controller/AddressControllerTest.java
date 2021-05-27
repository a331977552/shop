package org.shop.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.CustomerAddressAddVO;
import org.shop.model.vo.CustomerResultVO;
import org.shop.test.utils.BaseControllerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Log4j2
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AddressControllerTest extends BaseControllerTest<CustomerResultVO> {

	String token;

	@BeforeEach
	void setup(){
		final ResponseEntity<Result<String>> login = helper.login("junit_test", "a123456");
		token = login.getBody().getResult();
		helper.setPort(port);
		helper.setUIPath(getUipath());
	}

	private String getUipath() {
		return "/api/address";
	}

	@Test
	@Order(1)
	void addAddress() {
		CustomerAddressAddVO addVO= new CustomerAddressAddVO();
		addVO.setHomeAddress("");
		addVO.setPhoneNumber("1580300");
		addVO.setPostCode("4567");
		final ResponseEntity<Result<CustomerResultVO>> post = helper.builder().withToken(token).build().post(addVO, resVOReturnRef);
		System.out.println(post);
		Assertions.assertEquals(400,post.getStatusCodeValue());
		Assertions.assertEquals(2,post.getBody().getMsgDetail().split(",").length);
		CustomerAddressAddVO addVO2 = new CustomerAddressAddVO();
		addVO2.setHomeAddress("上海市浦东新区张江高科 陈辉路 1999号");
		addVO2.setPhoneNumber("13521834441");
		addVO2.setPostCode("200000");



		final ResponseEntity<Result<CustomerResultVO>> success = helper.builder().withToken(token).build().post(addVO2, resVOReturnRef);
		System.out.println(success);
		Assertions.assertEquals(200,success.getStatusCodeValue());
		Assertions.assertEquals(32,success.getBody().getResult().getId().length());

		final String id = success.getBody().getResult().getId();
		findAddressByID(id);
		this.deleteAddress(id, token);

	}

	void findAddressByID(String id) {
		final ResponseEntity<Result<CustomerResultVO>> resultResponseEntity = helper.builder().withToken(token).setUipath(getUipath() + "/"+id).build()
				.get(resVOReturnRef);
		System.out.println(resultResponseEntity);
		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue() );
		Assertions.assertEquals(32, resultResponseEntity.getBody().getResult().getId().length());
	}


	void deleteAddress(String id,String token) {
		final ResponseEntity<String> delete = helper.builder().setUipath(getUipath() + "/" + id).withToken(token).build().delete(strRef);
		System.out.println(delete);
		Assertions.assertEquals(200, delete.getStatusCodeValue());

	}

	@Test
	void findAddressesByCustomerID() {
		final ResponseEntity<Result<List<CustomerResultVO>>> resultResponseEntity =
				helper.builder().setUipath(getUipath() + "/customer").withToken(token).build()
				.get(resListVOReturnRef);
		System.out.println(resultResponseEntity);

		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue() );
		Assertions.assertEquals(true, resultResponseEntity.getBody().getResult().size()>0);
	}

	@Override
	protected ParameterizedTypeReference<Result<CustomerResultVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<CustomerResultVO>>() {
		};
	}

	@Override
	protected ParameterizedTypeReference<Result<List<CustomerResultVO>>> getParameterListTypeRef() {
		return super.getParameterListTypeRef();
	}
}