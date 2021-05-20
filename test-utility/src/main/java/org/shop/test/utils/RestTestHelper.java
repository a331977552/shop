package org.shop.test.utils;

import org.shop.common.Result;
import org.shop.common.security.AuthenticationEntity;
import org.shop.test.model.TestCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class RestTestHelper {

	@Autowired(required = false)
	private TestRestTemplate restTemplate;
	private int port;
	private String uipath;

	public RestTestHelper setPort(int port) {
		this.port = port;
		return this;
	}

	public RestTestHelper setUIPath(String uipath) {
		this.uipath = uipath;
		return this;
	}

	public RestTestBuilder builder() {
		return new RestTestBuilder(port, uipath, restTemplate);
	}

	public ResponseEntity<Result<String>> login(String username, String password) {
		ResponseEntity<Result<String>> post = builder().setUipath("/user/authenticate").build().post(new AuthenticationEntity(username, password), new ParameterizedTypeReference<Result<String>>() {
		});
		return post;
	}

	public ResponseEntity<Result<TestCustomerVO>> getUserInfo(String token)  {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add( HttpHeaders.AUTHORIZATION, "Bearer "+token);
		return builder().setUipath("/user").build().get(httpHeaders,new ParameterizedTypeReference<Result<TestCustomerVO>>(){},null);
	}

	public class RestTestBuilder {
		private int port;
		private String uipath;
		private TestRestTemplate restTemplate;
		private String token;

		public RestTestBuilder(int port, String uipath, TestRestTemplate restTemplate) {
			this.port = port;
			this.uipath = uipath;
			this.restTemplate = restTemplate;
		}

		public RestTestBuilder setUipath(String uipath) {
			this.uipath = uipath;
			return this;
		}

		public RestTestBuilder setPort(int port) {
			this.port = port;
			return this;
		}
		public RestTestBuilder withToken(String token){
			this.token = token;
			return this;
		}

		public TestHttpClient build() {
			return new TestHttpClient(port, uipath,token, restTemplate);
		}

	}


}
