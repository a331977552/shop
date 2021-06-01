package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.vo.OrderCreateVO;
import org.shop.model.vo.OrderItemCreateVO;
import org.shop.model.vo.OrderReturnVO;
import org.shop.test.utils.BaseControllerTest;
import org.shop.test.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest extends BaseControllerTest<OrderReturnVO> {

	@Autowired
	RestTestHelper helper;

	@LocalServerPort
	private int port;



	@Test
	void createOrder() {
		final String token = getToken();
		final OrderCreateVO orderCreateVO = new OrderCreateVO();
		List<OrderItemCreateVO> items =new ArrayList<>();

		final OrderItemCreateVO item = new OrderItemCreateVO();
		item.setUnitPrice(BigDecimal.valueOf(12));
		item.setProductId("00121c45097d4ed9906de3a2fbb3c4d3");
		item.setSubtotal(BigDecimal.valueOf(24));
		item.setQuantity(2);
		item.setSkuId(29);
		items.add(item);

		final OrderItemCreateVO item2 = new OrderItemCreateVO();
		item2.setUnitPrice(BigDecimal.valueOf(30));
		item2.setProductId("00121c45097d4ed9906de3a2fbb3c4d3");
		item2.setSubtotal(BigDecimal.valueOf(60));
		item2.setQuantity(2);
		item2.setSkuId(37);
		items.add(item2);

		orderCreateVO.setItems(items);
		orderCreateVO.setPhoneNumber("15908076523");
		orderCreateVO.setHomeAddress("北京市海淀区 御花园路22号");
		orderCreateVO.setPostCode("100001");
		orderCreateVO.setTotalPrice(BigDecimal.valueOf(84));
		final ResponseEntity<Result<OrderReturnVO>> post = helper.builder().withToken(token).setUipath("/api/order").setPort(port).build().post(orderCreateVO, resVOReturnRef);
		System.out.println(post);
		Assertions.assertEquals(200, post.getStatusCodeValue());

	}

	@Test
	void getOrder() {
		final String token = getToken();
		final ResponseEntity<Result<OrderReturnVO>> resultResponseEntity = helper.builder().setPort(port).setUipath("/api/order/666006ec474f464abd5fa9a49ae4765f").withToken(token).build().get(resVOReturnRef);
		System.out.println(resultResponseEntity);
		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue());
	}

	@Test
	void getAllOrderByPage() {

	}

	@Override
	protected ParameterizedTypeReference<Result<OrderReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<OrderReturnVO>>() {
		};
	}
}