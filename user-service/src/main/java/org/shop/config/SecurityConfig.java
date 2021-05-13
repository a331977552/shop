package org.shop.config;

import org.shop.filter.JwtAuthenticationFilter;
import org.shop.security.CustomAccessDeniedHandler;
import org.shop.security.JWTAuthenticationProvider;
import org.shop.security.RestAuthenticationEntryPoint;
import org.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	JWTAuthenticationProvider jwtAuthenticationProvider;
	@Autowired
	UserService service;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			jwtAuthenticationProvider.setPasswordEncoder(encoder());
			jwtAuthenticationProvider.setUserDetailsService(service);
			auth.authenticationProvider(jwtAuthenticationProvider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				csrf().disable().
				cors().configurationSource(corsConfigurationSource()).
				and().
				headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
				new Header("Access-control-Allow-Origin", "*"),
				new Header("Access-Control-Expose-Headers", "Authorization")))).
				and().
				authorizeRequests().antMatchers("/user/signup", "/user/authenticate").permitAll().anyRequest().authenticated().
				and().
				exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).authenticationEntryPoint(new RestAuthenticationEntryPoint()).
				and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
				addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).
				formLogin().disable();
	}


	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTION"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		configuration.addExposedHeader("Authorization");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
