package org.shop.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.shop.common.Result;
import org.shop.common.exception.IllegalUserAccessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
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
public class AuthenticationFailureHandler implements AuthenticationEntryPoint
{
    private ObjectMapper mapper = new ObjectMapper();
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> customHandler(AuthenticationException authException, WebRequest webRequest) throws IOException {
        log.debug("1 {}",authException.getMessage());
        final Result result = processInternalException(authException);

        return new ResponseEntity(result, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.debug("request path:{},{}",request.getServletPath(),authException.getMessage());
        final Result result = processInternalException(authException);
        final String value = mapper.writeValueAsString(result);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(value);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }


    private Result processInternalException(AuthenticationException exception){
        if(exception   instanceof IllegalUserAccessException){
            final Result unauthorized = Result.unauthorized(exception.getMessage());
            unauthorized.setMsg("警告 错误用户");
            return unauthorized;
        }

        return Result.unauthorized(exception.getMessage());
    }
}