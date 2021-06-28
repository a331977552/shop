package org.shop.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, String> {
	Pattern pattern;
	@Override
	public void initialize(EnumNamePattern constraintAnnotation) {
		try {
			pattern = Pattern.compile(constraintAnnotation.regexp());
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Given Regexp is valid: "+ constraintAnnotation.regexp(),e);
		}

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		Matcher m = pattern.matcher(value);
		return m.matches();
	}
}
