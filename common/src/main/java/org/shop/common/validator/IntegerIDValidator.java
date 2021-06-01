package org.shop.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IntegerIDValidator implements ConstraintValidator<IDValid,Integer> {
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if(value ==null || value.intValue() < 0 )
			return false;
		return true;
	}
}
