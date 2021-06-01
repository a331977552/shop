package org.shop.service.impl;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.common.util.BeanConvertor;
import org.shop.common.util.Page;
import org.shop.common.util.SecurityUtil;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.OrderCreationException;
import org.shop.mapper.OrderItemDAOMapper;
import org.shop.mapper.ShopOrderDAOMapper;
import org.shop.model.dao.OrderItemDAO;
import org.shop.model.dao.OrderItemDAOExample;
import org.shop.model.dao.ShopOrderDAO;
import org.shop.model.dao.ShopOrderDAOExample;
import org.shop.model.dto.SkuDTO;
import org.shop.model.vo.*;
import org.shop.remote.ProductServiceProxy;
import org.shop.service.OrderService;
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
	ShopOrderDAOMapper orderMapper;
	@Autowired
	OrderItemDAOMapper itemMapper;
	@Autowired
	ProductServiceProxy productService;

	@Transactional
	@Override
	public OrderReturnVO createOrder(OrderCreateVO vo) {

		String orderNumber = UUIDUtils.generateID();
		String customerId = SecurityUtil.getAuthenticatedUser().getId();
		ShopOrderDAO shopOrderDAO = new ShopOrderDAO();
		BeanUtils.copyProperties(vo, shopOrderDAO);
		final List<OrderItemCreateVO> items = vo.getItems();
		BigDecimal totalPrice = new BigDecimal(0);
		shopOrderDAO.setId(orderNumber);
		shopOrderDAO.setCustomerId(customerId);
		shopOrderDAO.setStatus("UNPAID");//'UNPAID','PAID','FINISHED','CLOSED
		for (OrderItemCreateVO item : items) {
			final SkuDTO result = getProductSKUByID(item.getSkuId()).getResult();

			if (!result.getPrice().equals(item.getUnitPrice()))
				throw new OrderCreationException("商品提交价格与实际价格不符，可能已被更新");
			BigDecimal subTotal = result.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
			item.setSubtotal(subTotal);
			item.setUnitPrice(result.getPrice());
			totalPrice = totalPrice.add(subTotal);
		}
		if (!totalPrice.equals(vo.getTotalPrice()))
			throw new OrderCreationException("订单总价与提交总价不等");
		shopOrderDAO.setUpdatedTime(LocalDateTime.now());
		shopOrderDAO.setCreatedTime(LocalDateTime.now());
		shopOrderDAO.setTotalPrice(totalPrice);
		orderMapper.insert(shopOrderDAO);
		for (OrderItemCreateVO item : items) {
			final OrderItemDAO orderItemDAO = BeanConvertor.convert(item, OrderItemDAO.class);
			orderItemDAO.setOrderId(shopOrderDAO.getId());
			orderItemDAO.setId(UUIDUtils.generateID());
			orderItemDAO.setCustomerId(customerId);
			orderItemDAO.setUpdatedTime(LocalDateTime.now());
			orderItemDAO.setCreatedTime(LocalDateTime.now());
			orderItemDAO.setSnapshotProductId("test");
			orderItemDAO.setThumbImg("");
			itemMapper.insert(orderItemDAO);
		}
		final List<OrderItemReturnVO> orderItemDAOS = findItemsByOrderID(shopOrderDAO.getId());
		final OrderReturnVO convert = BeanConvertor.convert(shopOrderDAO, OrderReturnVO.class);
		final List<OrderItemReturnVO> convert1 = BeanConvertor.convert(orderItemDAOS, OrderItemReturnVO.class);
		convert.setItems(convert1);
		return convert;
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

		ShopOrderDAOExample exam=new ShopOrderDAOExample();
		String orderBy = Optional.ofNullable(page.getOrderBy()).orElse("updated_time desc ");
		exam.setOrderByClause(orderBy+ " limit "+ page.getPageSize() +" offset "+ page.getOffset());
		final List<ShopOrderDAO> shopOrderDAOS = orderMapper.selectByExample(exam);
		final long count = orderMapper.countByExample(exam);
		final List<OrderReturnVO> collect = shopOrderDAOS.stream().map(this::convertToOrderVO).
				peek(vo -> vo.setItems(this.findItemsByOrderID(vo.getId()))).collect(Collectors.toList());
		return Page.createFrom(page, count,collect);
	}
}
