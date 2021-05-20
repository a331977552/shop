package org.shop.exception;

public class InvalidUsernameException extends UserOperationException{

	public InvalidUsernameException(String message) {
		super(message);
	}
}
