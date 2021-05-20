package org.shop.common.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {

	public JWTAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public JWTAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}
}
