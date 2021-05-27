package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.OrderCreateVO;
import org.shop.model.vo.OrderQueryVO;
import org.shop.model.vo.OrderReturnVO;

import java.util.List;

public interface OrderService {
    OrderReturnVO createOrder(OrderCreateVO orderCreateVO);

    OrderReturnVO findOrderById(String id);
    List<OrderReturnVO> findAllOrdersByUserId(String userId);
    Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page);
}