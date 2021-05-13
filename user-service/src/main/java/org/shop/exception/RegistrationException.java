package org.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RegistrationException extends RuntimeException{
	public RegistrationException(String s) {
		super(s);
	}
}
