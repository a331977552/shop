package org.shop;

import java.util.Arrays;
import java.util.stream.Stream;

public class TextUtil {

	public static boolean isEmpty(String... text) {
		long count = Arrays.stream(text).filter(str -> str == null || str.trim().equals("")).count();
		return count > 0;
	}
	public static boolean hasText(String text) {
		return text != null && !text.trim().equals("");
	}

	public static boolean hasEveryText(String... text) {
		long count = Arrays.stream(text).filter(str -> str != null && !str.trim().equals("")).count();
		return count == text.length;
	}


}
