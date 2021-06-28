package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.OrderCreateVO;
import org.shop.model.vo.OrderQueryVO;
import org.shop.model.vo.OrderReturnVO;
import org.shop.model.vo.OrderUpdateVO;

import java.util.List;

public interface OrderService {
    OrderReturnVO createOrder(OrderCreateVO orderCreateVO);

    void  updateOrder(OrderUpdateVO updateVO);
    OrderReturnVO findOrderById(String id);
    List<OrderReturnVO> findAllOrdersByUserId(String userId);
    Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page);
}