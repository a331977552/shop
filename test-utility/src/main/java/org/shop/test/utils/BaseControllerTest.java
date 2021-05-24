package org.shop.test.utils;

import org.shop.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseControllerTest<T> {

	@LocalServerPort
	protected int port;

	@Autowired
	protected RestTestHelper helper;
	protected ParameterizedTypeReference<Result<T>> resProReturnRef = getParameterTypeRef();

	protected abstract ParameterizedTypeReference<Result<T>> getParameterTypeRef();

	protected ParameterizedTypeReference<String> strRef = new ParameterizedTypeReference<String>() {};
	protected ParameterizedTypeReference<Result<String>> strResultRef = new ParameterizedTypeReference<Result<String>>() {};
	protected String getToken() {
		final ResponseEntity<Result<String>> post = helper.loginWithDefault();
		assertEquals(200, post.getBody().getCode());
		assertEquals(true, post.getBody().getResult() != null);
		final String token = post.getBody().getResult();
		System.out.println("login success with token: "+ token);
		return token;
	}
}
