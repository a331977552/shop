package org.shop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.shop.common.util.Page;
import org.shop.mapper.OrderItemDAOMapper;
import org.shop.mapper.ShopOrderDAOMapper;
import org.shop.model.dao.OrderItemDAO;
import org.shop.model.dao.OrderItemDAOExample;
import org.shop.model.dao.ShopOrderDAO;
import org.shop.model.vo.OrderCreateVO;
import org.shop.model.vo.OrderItemReturnVO;
import org.shop.model.vo.OrderQueryVO;
import org.shop.model.vo.OrderReturnVO;
import org.shop.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//			if(vo.getOrderItems().size() == 0){
//				throw new UnexpectedException("订单商品列表为空！");
//			}
//
//			if(!PAYMENT_METHODS.contains(vo.getPaymentMethod()))
//			{
//				throw new UnexpectedException("无法识别的支付方式！");
//			}
//			if(!DINING_METHOD.contains(vo.getDiningMethod()))
//			{
//				throw new UnexpectedException("无法识别的订餐方式！");
//			}
//
//			MerchantVO merchant = merchantService.findMerchantById(vo.getMerchant_id());
//			CustomerVO existingCustomer = customerService.findUserByPhoneOrId(vo.getCustomer());
//			if(existingCustomer == null){
//				vo.getCustomer().setId(null);
//				vo.getCustomer().setAuto_generated(true);
//				existingCustomer = customerService.addUser(vo.getCustomer());
//			}
//			Integer customerId = existingCustomer.getId();
//			DeliveryAddress address =new DeliveryAddress();
//			address.setAddress(vo.getAddress());
//			address.setName(vo.getCustomer().getName());
//			address.setPhone(vo.getCustomer().getPhone());
//			address.setUser_id(customerId);
//			address.setPostcode(null);
//			deliveryAddressMapper.insert(address);
//			List<OrderItemVO> orderItems = vo.getOrderItems();
//			OrderForm order = new OrderForm();
//			order.setBuyer(vo.getCustomer().getName());
//			order.setComment(vo.getComment());
//			//TODO 加 expire time， 有用户功能的时候
//			//        order.setExpired_time(Instant.now().);
//
//			long orderCode = orderFormMapper.selectOrderCode();
//
//			BigDecimal totalPrice =new BigDecimal(0);
//			int quantity=0;
//			for (OrderItemVO item : orderItems) {
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
//			}
//
//			String orderNumber = IDUtils.getOrderId(orderCode);
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
//
//
//			//
//
//			DeliveryAddressVO addressVO =new DeliveryAddressVO();
//			BeanUtils.copyProperties(address,addressVO);
//
//			OrderResultVO orderResultVO = new OrderResultVO();
//			BeanUtils.copyProperties(order,orderResultVO);
//			orderResultVO.setOrderItems(orderItems);
//			orderResultVO.setCustomer(existingCustomer);
//			orderResultVO.setAddress(addressVO);
//			orderResultVO.setMerchant(merchant);
			return null;
	}


	OrderItemReturnVO convertToItemVO(OrderItemDAO item){
		OrderItemReturnVO vo=new OrderItemReturnVO();
		BeanUtils.copyProperties(item,vo);

		return vo;
	}

	@Override
	public OrderReturnVO findOrderById(String id) {
		final ShopOrderDAO shopOrderDAO = orderMapper.selectByPrimaryKey(id);

		OrderReturnVO orderResultVO =new OrderReturnVO();
		BeanUtils.copyProperties(shopOrderDAO,orderResultVO);
		//find all corresponding items.
		OrderItemDAOExample oie =new OrderItemDAOExample();
		oie.createCriteria().andOrderIdEqualTo(id);
		List<OrderItemReturnVO> orderItemVOS = itemMapper.selectByExample(oie).stream().map(this::convertToItemVO).collect(Collectors.toList());
		orderResultVO.setItems(orderItemVOS);
//		//find the address
//		DeliveryAddress address = deliveryAddressMapper.selectByPrimaryKey(form.getDelivery_address_id());
//		DeliveryAddressVO addressVO =new DeliveryAddressVO();
//		BeanUtils.copyProperties(address,addressVO);
//		orderResultVO.setAddress(addressVO);
//		//find customer
//		if(form.getUser_id() != null){
//			CustomerVO customerVO = customerService.getUserById(form.getUser_id());
//			orderResultVO.setCustomer(customerVO);
//		}
		return null;
	}

	@Override
	public List<OrderReturnVO> findAllOrdersByUserId(String userId) {
		return null;
	}

	@Override
	public Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page) {
		return null;
	}
}
