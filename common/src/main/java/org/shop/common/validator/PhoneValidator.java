package org.shop.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PhoneValidator implements ConstraintValidator<IsPhone, String> {
	Pattern pattern;
	@Override
	public void initialize(IsPhone constraintAnnotation) {
		try {
			final PhoneType type = constraintAnnotation.type();
			switch (type){
				case HK:
					pattern = Pattern.compile(HKIPhoneValidator.pattern);
					break;
				case Chinese:
					pattern = Pattern.compile(ChineseIPhoneValidator.pattern);
					break;
			}
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Given Regexp is valid: "+ constraintAnnotation.type(),e);
		}

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return false;
		}

		Matcher m = pattern.matcher(value);
		return m.matches();
	}
}