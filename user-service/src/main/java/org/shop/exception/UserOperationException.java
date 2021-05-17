package org.shop.exception;

public abstract class UserOperationException extends RuntimeException{
	public UserOperationException(String message) {
		super(message);
	}

	public UserOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserOperationException(Throwable cause) {
		super(cause);
	}

	public UserOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
