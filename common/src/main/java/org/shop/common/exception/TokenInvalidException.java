package org.shop.common.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidTokenException extends AuthenticationException {
	public InvalidTokenException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public InvalidTokenException(String msg) {
		super(msg);
	}
}
