package org.shop.service.impl;

import org.junit.jupiter.api.Test;
import org.shop.model.vo.DeliveryCompanyAddVO;
import org.shop.model.vo.DeliveryCompanyReturnVO;
import org.shop.service.DeliveryCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest()
class DeliveryCompanyServiceImplTest {

	@Autowired
	DeliveryCompanyService service;

	@Test
	void addDeliveryCompany() {
		DeliveryCompanyAddVO company = new DeliveryCompanyAddVO();
		company.setName("圆通速度");
		company.setInfo("大公司");
		final DeliveryCompanyReturnVO deliveryCompanyReturnVO = service.addDeliveryCompany(company);
		System.out.println(deliveryCompanyReturnVO);
	}

	@Test
	void getAllDeliveryCompanies() {
		final List<DeliveryCompanyReturnVO> allDeliveryCompanies = service.getAllDeliveryCompanies();
		System.out.println(allDeliveryCompanies);

	}
}