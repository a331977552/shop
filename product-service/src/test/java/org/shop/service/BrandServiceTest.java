package org.shop.service;

import org.junit.jupiter.api.Test;
import org.shop.mapper.BrandDAOMapper;
import org.shop.model.dao.BrandDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class BrandServiceTest {

	@Autowired
	BrandDAOMapper mapper;

	@Test
	void testGET(){
		final BrandDAO brandDAO = mapper.selectByPrimaryKey(11);
		System.out.println(brandDAO);
	}

}
