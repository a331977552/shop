package org.shop.exception;

public class CategoryException extends RuntimeException {
	public CategoryException() {
	}

	public CategoryException(String message) {
		super(message);
	}

	public CategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryException(Throwable cause) {
		super(cause);
	}

	public CategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
