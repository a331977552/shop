package org.shop.service;

import org.shop.common.util.Page;
import org.shop.model.vo.*;

import java.util.List;

public interface OrderService {
    OrderReturnVO createOrder(OrderCreateVO orderCreateVO);

    void  updateOrder(OrderUpdateVO updateVO);
    OrderReturnVO findOrderById(String id);
    List<OrderReturnVO> findAllOrdersByUserId(String userId);
    void deleteOrder(String orderID);

    Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page);
}