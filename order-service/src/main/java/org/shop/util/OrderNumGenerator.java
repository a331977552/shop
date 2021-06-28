package org.shop.util;

import org.shop.common.RedisService;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class OrderNumGenerator {
	private OrderNumGenerator() {
	}

	static SimpleDateFormat redisKeyForm = new SimpleDateFormat("yyyy-MM-dd4");
	static SimpleDateFormat orderNumFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static final long DAY_IN_MILLISECONDS = 24 * 3600 * 1000;

	public static synchronized String generateOrderNum(RedisService redisService) {
		final String keySuffix = redisKeyForm.format(new Date());
		final Object o = redisService.get(getKey(keySuffix));
		if (o == null) {
			redisService.set(keySuffix + "_ORDER_KEY_SEQUENCE", 0, DAY_IN_MILLISECONDS);
		}
		final long increment = redisService.increment(getKey(keySuffix), 1);
		return  orderNumFormat.format(new Date())+String.format("%03d", increment);
	}

	private static String getKey(String format) {
		return "ORDER_KEY_SEQUENCE_" + format;
	}
}
