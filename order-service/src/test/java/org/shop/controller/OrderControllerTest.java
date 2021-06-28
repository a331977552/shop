package org.shop.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.Result;
import org.shop.model.OrderSource;
import org.shop.model.vo.*;
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
		List<OrderItemCreateVO> items = new ArrayList<>();

		final OrderItemCreateVO item = new OrderItemCreateVO();
		item.setUnitPrice(BigDecimal.valueOf(22));
		item.setProductId("49465d63b86340fbba40c4609423d61e");
		item.setSubtotal(BigDecimal.valueOf(66));
		item.setQuantity(3);
		item.setSkuId(68);
		items.add(item);

		final OrderItemCreateVO item2 = new OrderItemCreateVO();
		item2.setUnitPrice(BigDecimal.valueOf(22332));
		item2.setProductId("49465d63b86340fbba40c4609423d61e");
		item2.setSubtotal(BigDecimal.valueOf(22332));
		item2.setQuantity(1);
		item2.setSkuId(67);
		items.add(item2);

		orderCreateVO.setItems(items);
		orderCreateVO.setTotalPrice(BigDecimal.valueOf(22332+66));
		orderCreateVO.setAutoConfirmDays(15);
		orderCreateVO.setPayMethod("alipay");
		orderCreateVO.setOrderSource(OrderSource.android_app);
		ShippingAddressAddVO address = new ShippingAddressAddVO();
		address.setPhoneNumber("15908076523");
		address.setHomeAddress("北京市海淀区 御花园路22号");
		address.setPostCode("100001");
		address.setCustomerName("嘿嘿");
		orderCreateVO.setAddress(address);
		final ResponseEntity<Result<OrderReturnVO>> post = helper.builder().withToken(token).setUipath("/api/order").setPort(port).build().post(orderCreateVO, resVOReturnRef);
		System.out.println(post);
		Assertions.assertEquals(200, post.getStatusCodeValue());
	}

	@Test
	void updateOrder() {
		final String token = getToken();
		final OrderUpdateVO orderUpdateVO = new OrderUpdateVO();
		List<OrderItemUpdateVO> items = new ArrayList<>();

		final OrderItemUpdateVO item = new OrderItemUpdateVO();
		item.setUnitPrice(BigDecimal.valueOf(88));
		item.setSubtotal(BigDecimal.valueOf(176));
		item.setQuantity(2);
		item.setId("11c32e174c1d47d0a6b370eaea52de68");
		items.add(item);

		final OrderItemUpdateVO item2 = new OrderItemUpdateVO();
		item2.setUnitPrice(BigDecimal.valueOf(999));
		item2.setSubtotal(BigDecimal.valueOf(999*2));
		item2.setQuantity(2);
		item2.setId("7d078c5d350345a7a6c1b7be0267b846");
		items.add(item2);
		orderUpdateVO.setId("77b736825a5b4d68929f617085adc2ab");
		orderUpdateVO.setBuyerComment("请发货快点");
		orderUpdateVO.setSellerComment("顺丰发货");
		orderUpdateVO.setStatus("SHIPPED");
		orderUpdateVO.setPayMethod("wechat");
		orderUpdateVO.setItems(items);

		ShippingAddressUpdateVO address = new ShippingAddressUpdateVO();
		address.setId("5492b89fdd1a460c937a7f8f1483c3f2");
		address.setPhoneNumber("15908076500");
		address.setHomeAddress("上海市哈哈公司");
		address.setPostCode("20000");
		address.setCustomerName("嘿嘿");
		orderUpdateVO.setAddress(address);

		final ResponseEntity<String> post = helper.builder().withToken(token).setUipath("/api/order").setPort(port).build().put(orderUpdateVO);
		prettyPrint(post);
		Assertions.assertEquals(200, post.getStatusCodeValue());
	}
	@Test
	void getOrder() {
		final String token = getToken();
		final ResponseEntity<Result<OrderReturnVO>> resultResponseEntity = helper.builder().setPort(port).setUipath("/api/order/4f42aabf537c4d5690d46423e2824828").withToken(token).build().get(resVOReturnRef);
		prettyPrint(resultResponseEntity);
		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue());
	}

	@Test
	void getAllOrderByPage() {

		final String token = getToken();
		final ResponseEntity<Result<OrderReturnVO>> resultResponseEntity = helper.builder().setPort(port).setUipath("/api/order/4f42aabf537c4d5690d46423e2824828").withToken(token).build().get(resVOReturnRef);
		prettyPrint(resultResponseEntity);
		Assertions.assertEquals(200, resultResponseEntity.getStatusCodeValue());
	}

	@Override
	protected ParameterizedTypeReference<Result<OrderReturnVO>> getParameterTypeRef() {
		return new ParameterizedTypeReference<Result<OrderReturnVO>>() {
		};
	}
}