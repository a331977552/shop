package org.shop.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IDValidator implements ConstraintValidator<IDValid,String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value ==null || value.trim().length()!=32)
			return false;
		return true;
	}
}
