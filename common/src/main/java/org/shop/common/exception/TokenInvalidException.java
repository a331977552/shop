package org.shop.common.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenInvalidException extends AuthenticationException {
	public TokenInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public TokenInvalidException(String msg) {
		super(msg);
	}
}
