package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.common.RedisService;
import org.shop.test.utils.MultiTreadTestHelper;
import org.shop.util.OrderNumGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderNumGeneratorTest {


	@Autowired
	RedisService redisService;

	@Test
	public void test() {


		for (int i = 0; i < 30; i++) {
			final String orderNum = OrderNumGenerator.generateOrderNum(redisService);
			System.out.println(orderNum);
			System.out.println(orderNum.length());
		}



	}

	@Test
	public void testMultiThread() {

		List<RedisService> list = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			list.add(redisService);
		}


		MultiTreadTestHelper.testMultiThreadOn(list, (e) -> {
			final String s = OrderNumGenerator.generateOrderNum(e);
			System.out.println(s);
		});

	}
}
