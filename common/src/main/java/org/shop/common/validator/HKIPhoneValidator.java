package org.shop.common.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HKIPhoneValidator implements IPhoneValidator {
	public static String pattern=  "^(5|6|8|9)\\d{7}$";

	@Override
	public boolean validate(String phoneNumber) {
		String regExp =pattern;
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}
}
