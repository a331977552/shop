package org.shop.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.log4j.Log4j2;
import org.shop.Result;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//    ObjectMapper objectMapper = new ObjectMapper();
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        log.debug("commence{}",authException.getMessage());
//        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(objectMapper.writeValueAsString(Result.badRequest(authException.getMessage())));
//    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> customHandler(AuthenticationException authException, WebRequest webRequest) throws IOException {
        log.debug("{}",authException.getMessage());
        return new ResponseEntity(Result.badRequest(authException.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}