package org.shop.common.util;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public final class ErrorResultConvertor {

	public static String getErrorMsg(BindingResult result){
		return result.getAllErrors().stream().map(error->error.getDefaultMessage()).collect(Collectors.joining(","));

	}
}
