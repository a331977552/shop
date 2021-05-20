package org.shop.common.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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


	public static boolean isOneOf(String match,String ...array){
		Objects.requireNonNull(match);
		Objects.requireNonNull(array);
		return isOneOf(match, List.of(array));
	}
	public static boolean isOneOf(String match, List<String> array){
		return array.stream().anyMatch(str -> str.equals(match));
	}
}
