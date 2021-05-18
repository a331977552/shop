package org.shop.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Log4j2
public class JWTAuthenticationProvider implements AuthenticationProvider {


	private UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder;

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


	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		System.out.println("setPasswordEncoder");
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return this.passwordEncoder;
	}


	public void setUserDetailsService(UserDetailsService userDetailsService) {
		System.out.println("setUserDetailsService");
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return this.userDetailsService;
	}

}
