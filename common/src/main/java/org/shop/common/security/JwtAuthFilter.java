package org.shop.common.security;

import lombok.extern.log4j.Log4j2;
import org.shop.common.util.JwtTokenUtil;
import org.shop.common.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Log4j2
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	public JwtAuthFilter(JwtTokenUtil jwtTokenUtil) {
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (TextUtil.isEmpty(header) || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		// Get jwt token and validate
		final String token = header.split(" ")[1].trim();
		String username;
		if ((username = jwtTokenUtil.parseToken(token)) == null) {
			log.debug("authenticate failed on token : {}", token);
			filterChain.doFilter(request, response);
			return;
		}
		log.debug("authenticate on token : {}, result: {}", token, username);
		Authentication authenticate = authenticationManager.authenticate(new JWTAuthenticationToken(username, null));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		filterChain.doFilter(request, response);
	}


}