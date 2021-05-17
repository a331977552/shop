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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CommonResponseExceptionHandler
		extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Result result;
		if (status == HttpStatus.BAD_REQUEST) {
			result = Result.badRequest(ex.getLocalizedMessage());
		} else if (status == HttpStatus.INTERNAL_SERVER_ERROR) {
			result = Result.internalError(ex.getLocalizedMessage());
		} else if (status == HttpStatus.NOT_FOUND) {
			result = Result.notFound(ex.getLocalizedMessage());
		} else if (ex instanceof RegistrationException) {
			result = Result.badRequest(ex.getMessage());
		} else {
			result = Result.unknownError(ex.getLocalizedMessage());
		}
		log.debug(ex.getLocalizedMessage());
		return new ResponseEntity(result, headers, status);
	}




}