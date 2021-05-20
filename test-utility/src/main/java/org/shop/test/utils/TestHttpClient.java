package org.shop.test.utils;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public  class TestHttpClient {

		private int port;
		private String uipath;
		private TestRestTemplate restTemplate;

		public TestHttpClient(int port, String uipath, TestRestTemplate restTemplate) {
			this.port = port;
			this.uipath = uipath;
			this.restTemplate = restTemplate;
		}

		public  <T> ResponseEntity<T> post(ParameterizedTypeReference reference){
			return this.post(new HttpHeaders(),reference);
		}

		public  <T>ResponseEntity<T> post(HttpHeaders headers,ParameterizedTypeReference reference){
			return this.post(headers, null, reference);
		}

		public  <T>ResponseEntity<T> post(Object content, ParameterizedTypeReference reference){
			return this.post(new HttpHeaders(),content,reference);
		}

		public  <T> ResponseEntity<T> post(HttpHeaders headers,Object obj,ParameterizedTypeReference reference){
			return this.exchange(headers, HttpMethod.POST, obj,reference,null);
		}
		
		public <T>ResponseEntity<T> get(HttpHeaders headers, ParameterizedTypeReference reference,@Nullable Map<String,Object> urlVariables ) {
			return this.exchange(headers, HttpMethod.GET,null,reference, urlVariables);
		}

		private <T>ResponseEntity<T> exchange(HttpHeaders headers, HttpMethod method, @Nullable Object body, ParameterizedTypeReference reference, @Nullable Map<String,Object> urlVariables ) {
			if (urlVariables==null)
				urlVariables = new HashMap<>();
			HttpHeaders localHttpHeaders = new HttpHeaders();
			localHttpHeaders.addAll(headers);
			localHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(body,localHttpHeaders);
			ResponseEntity<T> resultResponseEntity = restTemplate.exchange("http://localhost:"+port+this.uipath,method,entity, reference,urlVariables);
			return resultResponseEntity;
		}

	public <T>ResponseEntity<T> put(HttpHeaders headers, Object result, ParameterizedTypeReference stringRef) {
		return this.exchange(headers,HttpMethod.PUT,result,stringRef, null);
	}
}