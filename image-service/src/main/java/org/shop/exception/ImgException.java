package org.shop.exception;

public class ImgException extends RuntimeException{
	public ImgException(String message) {
		super(message);
	}

	public ImgException(String message, Throwable cause) {
		super(message, cause);
	}
}
