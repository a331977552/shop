package org.shop.common.handler;

import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

/**
 * anonymous users trying to access restricted resources. 401
 * intercept AuthenticationException directly without implementing AuthenticationEntryPoint and registering it to security config.
 * because it the {@link GlobalExceptionFallbackHandler} will intercept before security does
 */
@Log4j2
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthenticationFailureHandler {    //implements AuthenticationEntryPoint

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> customHandler(AuthenticationException authException, WebRequest webRequest) throws IOException {
        log.debug("AuthenticationFailureHandler {}",authException.getMessage());
        return new ResponseEntity(Result.badRequest(authException.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}