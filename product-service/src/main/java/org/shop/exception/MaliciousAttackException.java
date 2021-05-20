package org.shop.exception;

public class MaliciousAttackException extends RuntimeException{
	public MaliciousAttackException(String message) {
		super(message);
	}
}
