package org.shop.controller;

import org.shop.common.Result;
import org.shop.model.vo.ShippingAddressReturnVO;
import org.shop.service.OrderShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/order/address")
public class OrderAddressController {

	@Autowired
	OrderShippingService shippingService;

	@GetMapping()
	public Result<ShippingAddressReturnVO> getOrderAddressByOrderId(
			@RequestParam("oid") String orderId
	) {
		return Result.of(shippingService.getOrderAddressByOrderId(orderId));
	}

}
