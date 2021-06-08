package org.shop.common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.shop.common.exception.TokenInvalidException;
import org.shop.common.exception.TokenExpiredException;
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
		try {
			username = jwtTokenUtil.parseToken(token);
		} catch (ExpiredJwtException ex) {
			//todo remove, because it doesn't work
			throw new TokenExpiredException("token expired",ex);
		} catch (JwtException exception) {
			//todo remove, because it doesn't work
			throw new TokenInvalidException("invalid token",exception);
		}

		log.debug("authenticate on token : {}, result: {}", token, username);
		Authentication authenticate = authenticationManager.authenticate(new JWTAuthenticationToken(username, null));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		filterChain.doFilter(request, response);
	}


}