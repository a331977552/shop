package org.shop.controller;

import org.junit.jupiter.api.Test;
import org.shop.Result;
import org.shop.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;


	@Test
	void testLogin(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<?> empty = new HttpEntity<>(headers);
		ResponseEntity<Result> resultResponseEntity = restTemplate.postForEntity("http://localhost:"+port+"/user/login",empty, Result.class);
		assertEquals( 400,resultResponseEntity.getStatusCodeValue());


		HttpEntity<?> empty2 = new HttpEntity<>(new CustomerVO(),headers);
		ResponseEntity<Result> response = restTemplate.postForEntity("http://localhost:"+port+"/user/login",empty2,Result.class);
		assertEquals( "用户名不能为空,密码不能为空",response.getBody().getMsgDetail());
		assertEquals( 400,response.getBody().getCode());

		CustomerVO customerVO=new CustomerVO();
		customerVO.setUsername("wrongname");
		customerVO.setPassword("wrongpassword");
		HttpEntity<CustomerVO> httpEntity = new HttpEntity<>(customerVO);
		ResponseEntity<Result> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/user/login",httpEntity, Result.class);
		assertEquals(400, responseEntity.getBody().getCode());
		assertEquals("用户名或密码错误", responseEntity.getBody().getMsgDetail());

		customerVO.setUsername("15803012301");
		customerVO.setPassword("a123451");
		ResponseEntity<Result> correct = restTemplate.postForEntity("http://localhost:"+port+"/user/login",httpEntity, Result.class);
		assertEquals(200, correct.getBody().getCode());
		assertEquals("成功", correct.getBody().getMsg());
	}




}
