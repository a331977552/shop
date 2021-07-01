package org.shop.service;

import org.shop.model.vo.OrderShippingInfoAddVO;
import org.shop.model.vo.OrderShippingInfoReturnVO;

public interface OrderShippingService {
	OrderShippingInfoReturnVO shipOrder(OrderShippingInfoAddVO addVO);

}
