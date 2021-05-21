package org.shop.handler;


import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.exception.ImgException;
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
public class ImgExceptionHandler
{
	@ExceptionHandler(ImgException.class)
	protected ResponseEntity<Result> handleMyExceptionInternal(Exception ex, WebRequest request) {
		Result result = Result.badRequest(ex.getMessage());
		log.debug("handle ImgException {}", ex.getLocalizedMessage());
		return new ResponseEntity(result, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
