package org.shop.common.exception;

import org.springframework.security.core.AuthenticationException;

public class IllegalUserAccessException extends AuthenticationException {
	public IllegalUserAccessException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public IllegalUserAccessException(String msg) {
		super(msg);
	}
}
