package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.DeliveryCompanyAddVO;
import org.shop.model.vo.DeliveryCompanyReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeliveryCompanyControllerTest extends BaseControllerTest<DeliveryCompanyReturnVO> {


	@Autowired
	RestTestHelper helper;

	@LocalServerPort
	private int port;



	@Test
	void add() {
		final String token = getToken();
		DeliveryCompanyAddVO vo=new DeliveryCompanyAddVO();
		vo.setName("顺丰速递");
		final ResponseEntity<Result<DeliveryCompanyReturnVO>> post = helper.builder().withToken(token).setUipath("/api/delivery_company").setPort(port).build().post(vo, resVOReturnRef);
		System.out.println(post);
		Assertions.assertEquals(200, post.getStatusCodeValue());

	}

	@Test
	void getAll() {
		final String token = getToken();
		final ResponseEntity<Result<List<DeliveryCompanyReturnVO>>> resultResponseEntity = helper.builder().withToken(token).setUipath("/api/delivery_company/all").setPort(port).build().get(resListVOReturnRef);


		System.out.println(resultResponseEntity.getBody());
		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue());
		Assertions.assertTrue( resultResponseEntity.getBody().getResult().size()>0);
	}

	@Override
	protected ParameterizedTypeReference<Result<DeliveryCompanyReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<DeliveryCompanyReturnVO>>() {
		};
	}
}