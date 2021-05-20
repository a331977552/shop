package org.shop.common.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JWTAuthenticationProvider implements AuthenticationProvider {

	private final MyUserService userDetailsService;

	public JWTAuthenticationProvider(MyUserService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername((String) authentication.getPrincipal());
		JWTAuthenticationToken jwtAuthenticationToken =new JWTAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),userDetails.getAuthorities());
		jwtAuthenticationToken.setDetails(userDetails);
		log.debug("JWTAuthenticationToken: {}",jwtAuthenticationToken);

		return jwtAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JWTAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
