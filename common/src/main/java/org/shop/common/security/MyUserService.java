package org.shop.common.security;

import org.shop.common.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {


	@Autowired
	RedisService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final AuthenticationEntity o = (AuthenticationEntity) service.get(username);
		return o;
	}


}
