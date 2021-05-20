package org.shop.test.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class RestTestHelper {

	@Autowired(required = false)
	private TestRestTemplate restTemplate;
	private int port;
	private String uipath;

	public void setPort(int port) {
		this.port = port;
	}
	public void setUIPath(String uipath) {
		this.uipath = uipath;
	}
	public RestTestBuilder builder(){
		return new RestTestBuilder(port,uipath,restTemplate);
	}

	public  class RestTestBuilder {
		private int port;
		private String uipath;
		private TestRestTemplate restTemplate;
		public RestTestBuilder(int port, String uipath, TestRestTemplate restTemplate) {
			this.port = port;
			this.uipath = uipath;
			this.restTemplate = restTemplate;
		}
		public RestTestBuilder setUipath(String uipath){
			this.uipath = uipath;
			return this;
		}
		public RestTestBuilder setPort(int port){
			this.port = port;
			return this;
		}
		public TestHttpClient build(){
			return new TestHttpClient(port,uipath,restTemplate);
		}

	}


}
