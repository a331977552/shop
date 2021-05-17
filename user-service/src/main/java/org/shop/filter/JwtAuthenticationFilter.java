package org.shop.filter;

import lombok.extern.log4j.Log4j2;
import org.shop.TextUtil;
import org.shop.model.vo.CustomerVO;
import org.shop.security.JWTAuthenticationToken;
import org.shop.utils.JwtTokenUtil;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
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
		CustomerVO result;
		if ((result = jwtTokenUtil.parseToken(token)) == null) {
			log.debug("authenticate failed on token : {}", token);
			filterChain.doFilter(request, response);
			return;
		}
		log.debug("authenticate on token : {}, result: {}", token, result);

		Authentication authenticate = authenticationManager.authenticate(new JWTAuthenticationToken(result.getUsername(), null));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		filterChain.doFilter(request, response);
	}


}