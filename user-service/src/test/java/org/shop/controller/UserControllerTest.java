package org.shop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.Constants;
import org.shop.RedisService;
import org.shop.Result;
import org.shop.UserApplication;
import org.shop.model.vo.CustomerVO;
import org.shop.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = UserApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@Autowired
	private RestTestHelper helper;

	ParameterizedTypeReference stringRef = new ParameterizedTypeReference<String>() {};
	ParameterizedTypeReference resStringRef = new ParameterizedTypeReference<Result<String>>() {};
	ParameterizedTypeReference cusRef = new ParameterizedTypeReference<Result<CustomerVO>>() {};
	@LocalServerPort
	private int port;

	@Autowired
	RedisService redisService;

	@BeforeEach
	void contextLoad(){
		helper.setPort(port);
		final String uipath = "/user/authenticate";
		helper.setUIPath(uipath);
		System.out.println(helper.hashCode());
	}
	@Test
	void testLogin1(){
		ResponseEntity<String> resultResponseEntity = helper.builder().build().post(stringRef) ;
		assertEquals( 400,resultResponseEntity.getStatusCodeValue());
	}

	@Test
	void testLogin2(){
		ResponseEntity<Result<CustomerVO>> response = helper.builder().build().post( new CustomerVO(),cusRef);
		System.out.println("用户名不能为空,密码不能为空 test");
		assertEquals( true,
				response.getBody().getMsgDetail().equals("用户名不能为空,密码不能为空")
						||response.getBody().getMsgDetail().equals("密码不能为空,用户名不能为空"));
	}
	@Test
	void testLogin3(){
		ResponseEntity<Result<CustomerVO>> responseEntity = helper.builder().build().post( new CustomerVO("wsdorng","pasasdss"),cusRef);
		assertEquals(400, responseEntity.getBody().getCode());
		assertEquals("用户名或密码错误", responseEntity.getBody().getMsgDetail());
	}
	@Test
	void testLogin4(){
		ResponseEntity<Result<String>> success = helper.builder().build().post(new CustomerVO("15803012301","a123451"),resStringRef);
		System.out.println(success);
		assertEquals(200,
				success.getStatusCodeValue());
		assertEquals("成功", success.getBody().getMsg());
	}

	@Test
	void testGetInfo(){
		ResponseEntity<Result<String>> success = helper.builder().build().post(new CustomerVO("15803012301","a123451"),resStringRef);
		System.out.println(success);
		assertEquals(200, success.getBody().getCode());
		assertEquals("成功", success.getBody().getMsg());
		helper.setUIPath("/user/getInfo");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add( HttpHeaders.AUTHORIZATION, "Bearer "+success.getBody().getResult());
		ResponseEntity<Result<CustomerVO>> post = helper.builder().build().post(httpHeaders,success.getBody().getResult(),cusRef);
		System.out.println(post);
		assertEquals(200, post.getBody().getCode());
		CustomerVO result1 = post.getBody().getResult();
		assertEquals("test", result1.getUsername());
	}

	@Test
	void testBlockLogin(){
		try {
			redisService.delete(Constants.REDIS_USER_LOGIN_ATTEMPT + "_127.0.0.1");
			for (int i = 0; i < 11; i++) {
				ResponseEntity<Result<CustomerVO>> resultResponseEntity = helper.builder().build().post(new CustomerVO(),cusRef);
				System.out.println(resultResponseEntity.getBody());
				assertEquals(400,resultResponseEntity.getStatusCodeValue());
			}
			ResponseEntity<Result<CustomerVO>> resultResponseEntity = helper.builder().build().post(new CustomerVO(),cusRef);
			System.out.println(resultResponseEntity.getBody());
			assertEquals( 400,resultResponseEntity.getStatusCodeValue());
//			assertEquals( "请求太过频繁",resultResponseEntity.getBody().getMsgDetail());
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			redisService.delete(Constants.REDIS_USER_LOGIN_ATTEMPT + "_127.0.0.1");
		}
	}

	@Test
	void testGetUserInfo(){
		ResponseEntity<Result<String>> success = helper.builder().setUipath("/user/authenticate").build().post(new CustomerVO("15803012301","a123451"),resStringRef);
		System.out.println(success);
		assertEquals(200, success.getStatusCodeValue());
		String token = success.getBody().getResult();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add( HttpHeaders.AUTHORIZATION, "Bearer "+token);
		ResponseEntity<Result<CustomerVO>> post = helper.builder().setUipath("/user").build().get(httpHeaders,cusRef,null);
		System.out.println(post);
		assertEquals(200, post.getBody().getCode());
		CustomerVO result1 = post.getBody().getResult();
		System.out.println(result1);
		assertEquals("15803012351", result1.getUsername());
	}



}
