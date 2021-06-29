package org.shop.service.impl;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.shop.common.RedisService;
import org.shop.common.Result;
import org.shop.common.util.*;
import org.shop.exception.OrderCreationException;
import org.shop.exception.OrderUpdateException;
import org.shop.mapper.OrderDAOMapper;
import org.shop.mapper.OrderItemDAOMapper;
import org.shop.mapper.ShippingAddressDAOMapper;
import org.shop.model.dao.*;
import org.shop.model.dto.SkuDTO;
import org.shop.model.vo.*;
import org.shop.remote.ProductServiceProxy;
import org.shop.service.OrderService;
import org.shop.util.OrderNumGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAOMapper orderMapper;
	@Autowired
	OrderItemDAOMapper itemMapper;
	@Autowired
	ShippingAddressDAOMapper shippingAddressDAOMapper;

	@Autowired
	ProductServiceProxy productService;
	@Autowired
	RedisService redisService;


	@Transactional
	@Override
	public OrderReturnVO createOrder(OrderCreateVO vo) {

		String orderID = UUIDUtils.generateID();
		String customerId = SecurityUtil.getAuthenticatedUser().getId();

		BigDecimal totalPrice = new BigDecimal(0);

		OrderDAO order  = populateOrder(vo, orderID, customerId);

		for (OrderItemCreateVO item :  vo.getItems()) {
			totalPrice = creteOrderItem(customerId,orderID, totalPrice, item);
		}

		order.setTotalPrice(totalPrice);
		if (!totalPrice.equals(vo.getTotalPrice()))
			throw new OrderCreationException("订单总价与提交总价不等");

		orderMapper.insert(order);

		createAddress(vo, orderID);
		final OrderReturnVO orderReturnVO = BeanConvertor.convert(order, OrderReturnVO.class);
		final List<OrderItemReturnVO> itemReturnVOs = BeanConvertor.convert(findItemsByOrderID(orderID), OrderItemReturnVO.class);
		orderReturnVO.setItems(itemReturnVOs);
		orderReturnVO.setAddress(getAddressByOrder(orderID));
		return orderReturnVO;
	}

	private ShippingAddressReturnVO getAddressByOrder(String orderID) {
		ShippingAddressDAOExample example =new ShippingAddressDAOExample();
		example.createCriteria().andOrderIdEqualTo(orderID);
		return BeanConvertor.convert(shippingAddressDAOMapper.selectByExample(example).get(0),ShippingAddressReturnVO.class);
	}

	private OrderDAO populateOrder(OrderCreateVO vo, String orderID, String customerId) {
		OrderDAO order = new OrderDAO();
		BeanUtils.copyProperties(vo, order);
		order.setId(orderID);
		order.setCustomerId(customerId);
		order.setStatus("UNPAID");//'UNPAID','PAID','SHIPPED','FINISHED','REFUND', 'CLOSED'
		order.setOrderSource(vo.getOrderSource().name());
		order.setAutoConfirmDays(Optional.ofNullable(vo.getAutoConfirmDays()).orElse(15));
		order.setOrderNum(OrderNumGenerator.generateOrderNum(redisService));
		order.setUpdatedTime(LocalDateTime.now());
		order.setCreatedTime(LocalDateTime.now());
		return order;
	}

	private BigDecimal creteOrderItem(String customerId, String orderId, BigDecimal totalPrice, OrderItemCreateVO item) {
		final SkuDTO sku = getProductSKUByID(item.getSkuId()).getResult();
		if (!item.getProductId().equals(sku.getProductId()))
			throw new OrderCreationException("提交商品与实际购买商品不符!");//恶意篡改
		if (!sku.getPrice().equals(item.getUnitPrice()))
			throw new OrderCreationException("商品提交价格与实际价格不符，可能已被更新");

		BigDecimal subTotal = sku.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
		totalPrice = totalPrice.add(subTotal);
		final OrderItemDAO orderItemDAO = BeanConvertor.convert(item, OrderItemDAO.class);
		orderItemDAO.setOrderId(orderId);
		orderItemDAO.setId(UUIDUtils.generateID());
		orderItemDAO.setCustomerId(customerId);
		orderItemDAO.setUpdatedTime(LocalDateTime.now());
		orderItemDAO.setCreatedTime(LocalDateTime.now());
		orderItemDAO.setSnapshotProductId("test");
		orderItemDAO.setSubtotal(subTotal);
		orderItemDAO.setUnitPrice(sku.getPrice());
		orderItemDAO.setProductSku(sku.getAttribute());
		orderItemDAO.setThumbImg(Optional.ofNullable(item.getThumbImg()).orElse(""));
		itemMapper.insert(orderItemDAO);
		return totalPrice;
	}

	private void createAddress(OrderCreateVO vo,String orderID) {
		final ShippingAddressAddVO address = vo.getAddress();
		ShippingAddressDAO shippingAddressDAO = new ShippingAddressDAO();
		shippingAddressDAO.setCreatedTime(LocalDateTime.now());
		shippingAddressDAO.setUpdatedTime(LocalDateTime.now());
		BeanUtils.copyProperties(address, shippingAddressDAO);

		shippingAddressDAO.setOrderId(orderID);
		shippingAddressDAO.setId(UUIDUtils.generateID());
		shippingAddressDAOMapper.insert(shippingAddressDAO);
	}

	@Override
	@Transactional
	public void updateOrder(OrderUpdateVO updateVO) {
		updateAddress(updateVO);
		BigDecimal totalPrice = updateItems(updateVO);
		updateOrder(updateVO, totalPrice);
	}


	private void updateAddress(OrderUpdateVO updateVO) {
		final ShippingAddressUpdateVO address = updateVO.getAddress();
		ShippingAddressDAO shippingAddressDAO = new ShippingAddressDAO();
		BeanUtils.copyProperties(address, shippingAddressDAO);
		shippingAddressDAO.setUpdatedTime(LocalDateTime.now());
		shippingAddressDAOMapper.updateByPrimaryKeySelective(shippingAddressDAO);
	}

	private void updateOrder(OrderUpdateVO updateVO, BigDecimal totalPrice) {
		OrderDAO orderDAO = new OrderDAO();
		BeanUtils.copyProperties(updateVO, orderDAO);
		orderDAO.setTotalPrice(totalPrice);
		orderDAO.setUpdatedTime(LocalDateTime.now());
		orderMapper.updateByPrimaryKeySelective(orderDAO);
	}

	private BigDecimal updateItems(OrderUpdateVO updateVO) {
		BigDecimal totalPrice = new BigDecimal(0);
		final List<OrderItemUpdateVO> items = updateVO.getItems();
		OrderItemDAOExample itemExample = new OrderItemDAOExample();
		itemExample.createCriteria().andOrderIdEqualTo(updateVO.getId());
		final long itemCount = itemMapper.countByExample(itemExample);
		if (itemCount != items.size()) {
			throw new OrderUpdateException("订单更新不能增加或删除条目,请重新创建订单!");
		}
		for (OrderItemUpdateVO item : items) {
			BigDecimal subTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			item.setSubtotal(subTotal);
			final OrderItemDAO orderItemDAO = new OrderItemDAO();
			BeanUtils.copyProperties(item, orderItemDAO);
			itemMapper.updateByPrimaryKeySelective(orderItemDAO);
			totalPrice = totalPrice.add(subTotal);
		}
		return totalPrice;
	}

	@Retry(name = "default")
	private Result<SkuDTO> getProductSKUByID(Integer id) {
		return productService.getSkuByID(id);
	}


	OrderItemReturnVO convertToItemVO(OrderItemDAO item) {
		OrderItemReturnVO vo = new OrderItemReturnVO();
		BeanUtils.copyProperties(item, vo);
		return vo;
	}

	OrderReturnVO convertToOrderVO(OrderDAO order) {
		OrderReturnVO vo = new OrderReturnVO();
		BeanUtils.copyProperties(order, vo);
		return vo;
	}

	@Override
	public OrderReturnVO findOrderById(String id) {

		final OrderDAO shopOrderDAO = orderMapper.selectByPrimaryKey(id);
		SecurityUtil.checkIfIllegalUser(shopOrderDAO.getCustomerId());
		OrderReturnVO orderResultVO = new OrderReturnVO();
		BeanUtils.copyProperties(shopOrderDAO, orderResultVO);
		orderResultVO.setItems(findItemsByOrderID(id));
		orderResultVO.setAddress(getAddressByOrder(id));
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
		var exam = new OrderDAOExample();
		exam.createCriteria().andCustomerIdEqualTo(userId);
		final List<OrderDAO> shopOrderDAOS = orderMapper.selectByExample(exam);
		return shopOrderDAOS.stream().map(this::convertToOrderVO).
				peek(vo -> vo.setItems(this.findItemsByOrderID(vo.getId()))).collect(Collectors.toList());
	}

	@Override
	public Page<OrderReturnVO> findAllOrders(OrderQueryVO example, Page<OrderQueryVO> page) {

		OrderDAOExample exam = new OrderDAOExample();
		final OrderDAOExample.Criteria criteria = exam.createCriteria();
		if(TextUtil.hasText(example.getUsername())){
			criteria.andUsernameLike("%"+example.getUsername()+"%");
		}
		if(TextUtil.hasText(example.getOrderSource())){
			criteria.andOrderSourceEqualTo(example.getOrderSource());
		}
		if(TextUtil.hasText(example.getOrderNum())){
			criteria.andOrderNumLike("%"+example.getOrderNum()+"%");
		}
		if(TextUtil.hasText(example.getStatus())){
			criteria.andStatusEqualTo(example.getStatus());
		}
		if(TextUtil.hasText(example.getReceiverName())){
			ShippingAddressDAOExample addExample = new ShippingAddressDAOExample();
			addExample.createCriteria().andCustomerNameLike("%"+example.getReceiverName()+"%");
			final List<ShippingAddressDAO> shippingAddressDAOS = shippingAddressDAOMapper.selectByExample(addExample);
			final List<String> ids = shippingAddressDAOS.stream().map(ShippingAddressDAO::getOrderId).collect(Collectors.toList());
			criteria.andIdIn(ids);
		}
		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("created_time desc ");
		exam.setOrderByClause(orderBy + " limit " + page.getPageSize() + " offset " + page.getOffset());
		final List<OrderDAO> shopOrderDAOS = orderMapper.selectByExample(exam);
		final long count = orderMapper.countByExample(exam);
		final List<OrderReturnVO> collect = shopOrderDAOS.stream().map(this::convertToOrderVO).
				peek(vo -> vo.setItems(this.findItemsByOrderID(vo.getId()))).collect(Collectors.toList());
		return Page.createFrom(page, count, collect);
	}
}
