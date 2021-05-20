package org.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.shop.common.util.UUIDUtils;

public class IDTests {

	@Test
	public void testID(){
		String s = UUIDUtils.generateID();
		System.out.println(s);
		Assertions.assertEquals(30, s.length());
	}
}
