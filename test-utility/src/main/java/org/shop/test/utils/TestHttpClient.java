package org.shop.test.utils;

import org.shop.common.Result;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public class TestHttpClient {

	private final String token;
	private int port;
	private String uipath;
	private TestRestTemplate restTemplate;
	public static final ParameterizedTypeReference strResRef = new ParameterizedTypeReference<Result<String>>(){};

	public TestHttpClient(int port,  String uipath,String token, TestRestTemplate restTemplate) {
		this.port = port;
		this.uipath = uipath;
		this.token = token;
		this.restTemplate = restTemplate;
	}

	public <T> ResponseEntity<T> post(ParameterizedTypeReference<T> reference) {
		return this.post(new HttpHeaders(), reference);
	}

	public <T> ResponseEntity<T> post(HttpHeaders headers, ParameterizedTypeReference<T> reference) {
		return this.post(headers, null, reference);
	}

	public <T> ResponseEntity<T> post(Object content, ParameterizedTypeReference<T> reference) {
		return this.post(new HttpHeaders(), content, reference);
	}
	public <T> ResponseEntity<T> delete(ParameterizedTypeReference<T> reference,Map<String, Object> urlVariables) {
		return this.exchange(new HttpHeaders(),HttpMethod.DELETE, null, reference,urlVariables);
	}

	public <T> ResponseEntity<T> delete(ParameterizedTypeReference<T> reference) {
		return (ResponseEntity<T>)this.exchange(new HttpHeaders(),HttpMethod.DELETE, null, reference,null);
	}
	public  ResponseEntity<String> delete() {
		return this.exchange(new HttpHeaders(),HttpMethod.DELETE, null, strResRef,null);
	}

	public <T> ResponseEntity<T> post(HttpHeaders headers, Object obj, ParameterizedTypeReference<T> reference) {
		return this.exchange(headers, HttpMethod.POST, obj, reference, null);
	}

	public <T> ResponseEntity<T> get(HttpHeaders headers, ParameterizedTypeReference<T> reference, @Nullable Map<String, Object> urlVariables) {
		return this.exchange(headers, HttpMethod.GET, null, reference, urlVariables);
	}


	public <T> ResponseEntity<T> get(ParameterizedTypeReference<T> reference) {
		return this.exchange(null, HttpMethod.GET, null, reference, null);
	}
	private <T> ResponseEntity<T> exchange(HttpHeaders headers, HttpMethod method, @Nullable Object body, ParameterizedTypeReference<T> reference, @Nullable Map<String, Object> urlVariables) {
		if (urlVariables == null)
			urlVariables = new HashMap<>();
		HttpHeaders localHttpHeaders = new HttpHeaders();
		if(headers!=null){
			localHttpHeaders.addAll(headers);
		}
		if(headers ==null || headers.getContentType() == null){
			localHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
		}

		if(this.token!=null){
			localHttpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer "+token);
		}
		HttpEntity<?> entity = new HttpEntity<>(body, localHttpHeaders);
		ResponseEntity<T> resultResponseEntity = restTemplate.exchange("http://localhost:" + port + this.uipath, method, entity, reference, urlVariables);
		return resultResponseEntity;
	}

	public <T> ResponseEntity<T> put(HttpHeaders headers, Object result, ParameterizedTypeReference<T> stringRef) {
		return this.exchange(headers, HttpMethod.PUT, result, stringRef, null);
	}


}