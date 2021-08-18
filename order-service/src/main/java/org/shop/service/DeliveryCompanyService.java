package org.shop.service;

import org.shop.model.vo.DeliveryCompanyAddVO;
import org.shop.model.vo.DeliveryCompanyReturnVO;

import java.util.List;

public interface DeliveryCompanyService {


	DeliveryCompanyReturnVO addDeliveryCompany(DeliveryCompanyAddVO companyAddVO);

	List<DeliveryCompanyReturnVO> getAllDeliveryCompanies();
}
