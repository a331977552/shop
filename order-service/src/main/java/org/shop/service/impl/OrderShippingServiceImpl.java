package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.UUIDUtils;
import org.shop.exception.OrderException;
import org.shop.mapper.OrderShippingInfoDAOMapper;
import org.shop.mapper.ShippingAddressDAOMapper;
import org.shop.model.dao.OrderShippingInfoDAO;
import org.shop.model.dao.ShippingAddressDAO;
import org.shop.model.dao.ShippingAddressDAOExample;
import org.shop.model.vo.OrderShippingInfoAddVO;
import org.shop.model.vo.OrderShippingInfoReturnVO;
import org.shop.model.vo.ShippingAddressReturnVO;
import org.shop.service.OrderShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderShippingServiceImpl implements OrderShippingService {

	@Autowired
	OrderShippingInfoDAOMapper orderShippingInfoDAOMapper;

	@Autowired
	ShippingAddressDAOMapper shippingAddressDAOMapper;

	@Override
	public OrderShippingInfoReturnVO shipOrder(OrderShippingInfoAddVO addVO) {
		final OrderShippingInfoDAO dao = BeanConvertor.convert(addVO, OrderShippingInfoDAO.class);
		dao.setId(UUIDUtils.generateID());

		orderShippingInfoDAOMapper.insert(dao);
		return BeanConvertor.convert(dao, OrderShippingInfoReturnVO.class);
	}

	@Override
	public ShippingAddressReturnVO getOrderAddressByOrderId(String orderId) {
		ShippingAddressDAOExample example =new ShippingAddressDAOExample();
		final ShippingAddressDAOExample.Criteria criteria = example.createCriteria();
		criteria.andSOrderIdEqualTo(orderId);
		final List<ShippingAddressDAO> shippingAddressDAOS = shippingAddressDAOMapper.selectByExample(example);
		if(shippingAddressDAOS.size() == 0)
			throw new OrderException("该订单没有发货地址,请检查订单号!");
		return BeanConvertor.convert(shippingAddressDAOS.get(0),ShippingAddressReturnVO.class);
	}
}
