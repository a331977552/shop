package org.shop.service;

import org.shop.model.vo.OrderShippingInfoAddVO;
import org.shop.model.vo.OrderShippingInfoReturnVO;
import org.shop.model.vo.ShippingAddressReturnVO;

public interface OrderShippingService {
	OrderShippingInfoReturnVO shipOrder(OrderShippingInfoAddVO addVO);

	ShippingAddressReturnVO getOrderAddressByOrderId(String orderId);
}