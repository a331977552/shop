package org.shop.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class TestHelper {

	@Autowired(required = false)
	private TestRestTemplate restTemplate;

}
