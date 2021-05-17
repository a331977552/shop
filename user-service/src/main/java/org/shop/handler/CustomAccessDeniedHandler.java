package org.shop.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.shop.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//signed in ,but doesn't have the permission.
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log4j2
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	ObjectMapper objectMapper = new ObjectMapper();

	@ExceptionHandler(AccessDeniedException.class)
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		log.debug(e);
		httpServletResponse.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(Result.accessDenied(e.getMessage())));

	}
}
