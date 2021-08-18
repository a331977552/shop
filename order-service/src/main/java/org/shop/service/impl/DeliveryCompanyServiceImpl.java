package org.shop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.shop.common.util.BeanConvertor;
import org.shop.mapper.DeliveryCompanyDAOMapper;
import org.shop.model.dao.DeliveryCompanyDAO;
import org.shop.model.dao.DeliveryCompanyDAOExample;
import org.shop.model.vo.DeliveryCompanyAddVO;
import org.shop.model.vo.DeliveryCompanyReturnVO;
import org.shop.service.DeliveryCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class DeliveryCompanyServiceImpl implements DeliveryCompanyService {
	@Autowired
	DeliveryCompanyDAOMapper mapper;

	@Override
	public DeliveryCompanyReturnVO addDeliveryCompany(DeliveryCompanyAddVO companyAddVO) {

		final DeliveryCompanyDAO dao = BeanConvertor.convert(companyAddVO, DeliveryCompanyDAO.class);
		mapper.insert(dao);

		return BeanConvertor.convert(dao, DeliveryCompanyReturnVO.class);
	}

	@Override
	public List<DeliveryCompanyReturnVO> getAllDeliveryCompanies() {
		final List<DeliveryCompanyDAO> deliveryCompanyDAOS = mapper.selectByExample(new DeliveryCompanyDAOExample());
		return BeanConvertor.convert(deliveryCompanyDAOS,DeliveryCompanyReturnVO.class);
	}
}
