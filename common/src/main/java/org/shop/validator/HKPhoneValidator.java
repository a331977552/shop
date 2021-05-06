package org.shop.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HKPhoneValidator implements PhoneValidator{
	@Override
	public boolean validate(String phoneNumber) {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phoneNumber);
		return m.matches();
	}
}
