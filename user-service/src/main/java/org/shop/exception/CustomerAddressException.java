package org.shop.exception;

public class CustomerAddressException extends RuntimeException{
	public CustomerAddressException() {
	}

	public CustomerAddressException(String message) {
		super(message);
	}

	public CustomerAddressException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerAddressException(Throwable cause) {
		super(cause);
	}

	public CustomerAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
