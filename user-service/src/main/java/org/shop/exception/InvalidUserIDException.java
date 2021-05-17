package org.shop.exception;

public class InvalidUserIDException extends UserOperationException{

	public InvalidUserIDException(String message) {
		super(message);
	}
}
