package org.shop.validator;

import org.springframework.context.annotation.Bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ChinesePhoneValidator implements PhoneValidator{
	@Override
	public boolean validate(String phoneNumber) {
		return isLegal(phoneNumber);
	}

	private boolean isLegal(String str) throws PatternSyntaxException {
		String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
				"|(18[0-9])|(19[8,9]))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
}
