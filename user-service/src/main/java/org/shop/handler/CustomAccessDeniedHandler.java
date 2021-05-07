package org.shop.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	final ObjectMapper mapper;
	public CustomAccessDeniedHandler(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		httpServletResponse.getWriter().write(mapper.writeValueAsString(Result.accessDenied(e.getMessage())));

	}
}
