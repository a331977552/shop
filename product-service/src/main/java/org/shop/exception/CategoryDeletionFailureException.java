package org.shop.exception;

public class CategoryDeletionFailureException extends CategoryException {
	public CategoryDeletionFailureException() {
	}

	public CategoryDeletionFailureException(String message) {
		super(message);
	}

	public CategoryDeletionFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryDeletionFailureException(Throwable cause) {
		super(cause);
	}

	public CategoryDeletionFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
