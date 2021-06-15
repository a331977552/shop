package org.shop.config;

import org.shop.common.handler.AuthenticationFailureHandler;
import org.shop.common.handler.CustomAccessDeniedHandler;
import org.shop.common.security.JWTAuthenticationProvider;
import org.shop.common.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtAuthFilter jwtAuthFilter;
	@Autowired
	JWTAuthenticationProvider jwtAuthenticationProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.authenticationProvider(jwtAuthenticationProvider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
				.ignoring()
				.antMatchers("/resources/**","/static/**","**/favicon.ico");

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
				csrf().disable().
				cors().configurationSource(corsConfigurationSource()).
				and().
				authorizeRequests().
				requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
				antMatchers(HttpMethod.GET,"/api/product/**","api/product/test/**",
						"/api/category/**").permitAll().

				antMatchers(HttpMethod.POST, "/api/product").hasAuthority("ADMIN").
				antMatchers(HttpMethod.POST, "/api/category").hasAuthority("ADMIN").
				antMatchers(HttpMethod.PUT, "/api/product").hasAuthority("ADMIN").
				antMatchers(HttpMethod.PUT, "/api/product").hasAuthority("ADMIN").
				antMatchers(HttpMethod.PUT, "/api/category").hasAuthority("ADMIN").
				antMatchers( "/api/product/sku/*").hasAuthority("ADMIN").
				antMatchers(HttpMethod.DELETE, "/api/product/*").hasAuthority("ADMIN").
				antMatchers(HttpMethod.DELETE, "/api/category/*").hasAuthority("ADMIN").
				anyRequest().hasAnyAuthority("ADMIN").
				and().
				exceptionHandling().authenticationEntryPoint(new AuthenticationFailureHandler()).
				accessDeniedHandler(new CustomAccessDeniedHandler()).
				and().
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
				addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).
				formLogin().disable();
	}


	protected CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST","DELETE","PUT", "HEAD", "OPTION"));
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

