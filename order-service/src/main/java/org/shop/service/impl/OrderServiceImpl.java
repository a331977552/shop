package org.shop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.shop.common.util.Page;
import org.shop.common.util.SecurityUtil;
import org.shop.common.util.UUIDUtils;
import org.shop.mapper.OrderItemDAOMapper;
import org.shop.mapper.ShopOrderDAOMapper;
import org.shop.model.dao.OrderItemDAO;
import org.shop.model.dao.OrderItemDAOExample;
import org.shop.model.dao.ShopOrderDAO;
import org.shop.model.dao.ShopOrderDAOExample;
import org.shop.model.vo.*;
import org.shop.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	ShopOrderDAOMapper orderMapper;
	@Autowired
	OrderItemDAOMapper itemMapper;

	@Override
	public OrderReturnVO createOrder(OrderCreateVO vo) {

		final Integer customerId = vo.getCustomerId();
		String orderNumber = UUIDUtils.generateID();

		ShopOrderDAO shopOrderDAO =new ShopOrderDAO();
		BeanUtils.copyProperties(vo, shopOrderDAO);
		final List<OrderItemCreateVO> items = vo.getItems();
		BigDecimal totalPrice = new BigDecimal(0);
		shopOrderDAO.setTotalPrice(totalPrice);
		shopOrderDAO.setCustomerId(orderNumber);
		for (OrderItemCreateVO item : items) {
//				ProductVO product = productService.getProductById(item.getProduct_id());
//				BigDecimal subTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
//				item.setSub_total(subTotal);
//				item.setUniprice(product.getPrice());
//				item.setImg(product.getImgs().get(0).getUrl());
//				item.setImg(product.getImgs().get(0).getThumbnail_url());
//				item.setProduct_desc(product.getDescription());
//				item.setProduct_name(product.getName());
//				quantity += item.getQuantity();
//				totalPrice = totalPrice.add(subTotal);
		}
//
//			order.setPayment_method(vo.getPaymentMethod());
//			order.setDining_method(vo.getDiningMethod());
//			order.setDelivery_address_id(address.getId());
//			order.setOrder_code(orderCode+"");
//			order.setOrder_number(orderNumber);
//			order.setCreate_time(new Date());
//			order.setUpdate_time(order.getCreate_time());
//			order.setPhone(vo.getCustomer().getPhone());
//			order.setStatus(OrderConstants.STATUS_UNPAID);
//			order.setUser_id(customerId);
//			order.setMerchant_id(merchant.getId());
//			order.setTotal_price(totalPrice);
//			order.setTotal_count(quantity);
//			int result = orderFormMapper.insert(order);
//			if(result != 1)
//				throw new UnexpectedException("订单创建异常! 10001");
//			Integer orderId = order.getId();
//			for (OrderItemVO item : orderItems) {
//				item.setOrder_id(orderId);
//				OrderItem orderItem = new OrderItem();
//				BeanUtils.copyProperties(item,orderItem);
//				int insert = orderItemMapper.insert(orderItem);
//				item.setId(orderItem.getId());
//				if(insert != 1){
//					throw new UnexpectedException("订单创建异常! 10002");
//				}
//			}

		return null;
	}


	OrderItemReturnVO convertToItemVO(OrderItemDAO item) {
		OrderItemReturnVO vo = new OrderItemReturnVO();
		BeanUtils.copyProperties(item, vo);

		return vo;
	}

	OrderReturnVO convertToOrderVO(ShopOrderDAO order) {
		OrderReturnVO vo = new OrderReturnVO();
		BeanUtils.copyProperties(order, vo);

		return vo;
	}

	@Override
	public OrderReturnVO findOrderById(String id) {

		final ShopOrderDAO shopOrderDAO = orderMapper.selectByPrimaryKey(id);
		SecurityUtil.checkIfIllegalUser(shopOrderDAO.getCustomerId());
		OrderReturnVO orderResultVO = new OrderReturnVO();
		BeanUtils.copyProperties(shopOrderDAO, orderResultVO);


		orderResultVO.setItems(findItemsByOrderID(id));
		return orderResultVO;
	}

	private List<OrderItemReturnVO> findItemsByOrderID(String id) {

		//find all corresponding items.
		OrderItemDAOExample oie = new OrderItemDAOExample();
		oie.createCriteria().andOrderIdEqualTo(id);
		return itemMapper.selectByExample(oie).stream().map(this::convertToItemVO).collect(Collectors.toList());
	}

	@Override
	public List<OrderReturnVO> findAllOrdersByUserId(String userId) {
		ShopOrderDAOExample exam = new ShopOrderDAOExample();
		exam.createCriteria().andCustomerIdEqualTo(userId);
		final List<ShopOrderDAO> shopOrderDAOS = orderMapper.selectByExample(exam);
		return shopOrderDAOS.stream().map(this::convertToOrderVO).
				peek(vo -> vo.setItems(this.findItemsByOrderID(vo.getId()))).collect(Collectors.toList());
	}

	@Override
	public Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page) {

		return null;
	}
}
