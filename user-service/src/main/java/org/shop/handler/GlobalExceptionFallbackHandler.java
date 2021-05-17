package org.shop.handler;

import lombok.extern.log4j.Log4j2;
import org.shop.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice()
@Log4j2
public class GlobalExceptionFallbackHandler {

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		Result result = Result.unknownError(ex.getLocalizedMessage());
		log.error(ex.getMessage());
		return new ResponseEntity(result, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
