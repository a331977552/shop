package org.shop.controller;

import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.ShippingAddressReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShippingAddressControllerTest extends BaseControllerTest<ShippingAddressReturnVO> {

	@Autowired
	RestTestHelper helper;

	@LocalServerPort
	private int port;

	@Test
	void getAddress() {
		final String token = getToken();
		final ResponseEntity<Result<ShippingAddressReturnVO>> resultResponseEntity = helper.builder().setPort(port).setUipath("/api/order/address?oid=77b736825a5b4d68929f617085adc2ab").
				withToken(token).build().get(resVOReturnRef);
		prettyPrint(resultResponseEntity);
	}

	@Override
	protected ParameterizedTypeReference<Result<ShippingAddressReturnVO>> getParameterTypeRef() {
		return  new ParameterizedTypeReference<Result<ShippingAddressReturnVO>>() {
		};
	}
}