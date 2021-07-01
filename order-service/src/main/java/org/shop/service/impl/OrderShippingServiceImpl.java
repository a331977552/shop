package org.shop.service.impl;

import org.shop.common.util.BeanConvertor;
import org.shop.common.util.UUIDUtils;
import org.shop.mapper.OrderShippingInfoDAOMapper;
import org.shop.model.dao.OrderShippingInfoDAO;
import org.shop.model.vo.OrderShippingInfoAddVO;
import org.shop.model.vo.OrderShippingInfoReturnVO;
import org.shop.service.OrderShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderShippingServiceImpl implements OrderShippingService {

	@Autowired
	OrderShippingInfoDAOMapper orderShippingInfoDAOMapper;

	@Override
	public OrderShippingInfoReturnVO shipOrder(OrderShippingInfoAddVO addVO) {
		final OrderShippingInfoDAO dao = BeanConvertor.convert(addVO, OrderShippingInfoDAO.class);
		dao.setId(UUIDUtils.generateID());

		orderShippingInfoDAOMapper.insert(dao);
		return BeanConvertor.convert(dao, OrderShippingInfoReturnVO.class);
	}
}
