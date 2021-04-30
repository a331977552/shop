package org.shop;

import java.util.UUID;

public final class UUIDUtils {

	public static String generateID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
