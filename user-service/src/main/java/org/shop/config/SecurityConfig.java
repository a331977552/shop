package org.shop.config;

import org.shop.common.handler.AuthenticationFailureHandler;
import org.shop.common.handler.CustomAccessDeniedHandler;
import org.shop.common.security.JWTAuthenticationProvider;
import org.shop.common.security.JwtAuthFilter;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthFilter jwtAuthFilter;

	@Autowired
	CorsConfigurationSource corsConfigurationSource;

	@Autowired
	JWTAuthenticationProvider jwtAuthenticationProvider;
	@Autowired
	UserService service;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.authenticationProvider(jwtAuthenticationProvider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.
				csrf().disable().
				cors().configurationSource(corsConfigurationSource).
				and().
				authorizeRequests().
				requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.antMatchers("/user/signup", "/user/authenticate").permitAll().
				antMatchers("/api/address/*" ).hasAnyAuthority("CUSTOMER","ADMIN").
				anyRequest().hasAnyAuthority("CUSTOMER","ADMIN").//accessDecisionManager(accessDecisionManager()).
				and().
				/**
				 * warning, GLOBAL exception handler will catch the authentication exception before this security exception handling does.
				 */
				exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).authenticationEntryPoint(new AuthenticationFailureHandler()).
				and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//				addFilter()
				addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).
				formLogin().disable();
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}



	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}

