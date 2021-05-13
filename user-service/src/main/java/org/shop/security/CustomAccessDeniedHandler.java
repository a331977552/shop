package org.shop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shop.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//signed in ,but doesn't have the permission.
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
		System.out.println("CustomAccessDeniedHandler: "+e.getMessage());
		httpServletResponse.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
		httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.getWriter().write(objectMapper.writeValueAsString(Result.accessDenied(e.getMessage())));

	}
}
