package org.shop.handler;


import lombok.extern.log4j.Log4j2;
import org.shop.Result;
import org.shop.exception.InvalidUserIDException;
import org.shop.exception.RegistrationException;
import org.shop.exception.UserOperationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserOperationExceptionHandler
{
	@ExceptionHandler(UserOperationException.class)
	protected ResponseEntity<Object> handleMyExceptionInternal(Exception ex, WebRequest request) {
		Result result;
		if (ex instanceof RegistrationException) {
			result = Result.badRequest(ex.getMessage());
		} else if (ex instanceof InvalidUserIDException) {
			result = Result.badRequest(ex.getMessage());
		} else {
			result = Result.unknownError(ex.getLocalizedMessage());
		}
		log.debug("handleMyExceptionInternal {}", ex.getLocalizedMessage());
		return new ResponseEntity(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
