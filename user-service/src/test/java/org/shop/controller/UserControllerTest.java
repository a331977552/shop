package org.shop.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shop.Constants;
import org.shop.RedisService;
import org.shop.Result;
import org.shop.UserApplication;
import org.shop.mapper.CustomerDAOMapper;
import org.shop.model.vo.CustomerVO;
import org.shop.utils.RestTestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = UserApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	@Autowired
	private RestTestHelper helper;
	@Autowired
	CustomerDAOMapper customerDAOMapper;

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
	Result<String> successfulLogin(){
		ResponseEntity<Result<String>> success = this.login("15803012301", "a123451");
		System.out.println(success);
		assertEquals(200,
				success.getStatusCodeValue());
		assertEquals("成功", success.getBody().getMsg());
		return success.getBody();
	}

	ResponseEntity<Result<String>> login(String username, String password){
		ResponseEntity<Result<String>> post = helper.builder().setUipath("/user/authenticate").build().post(new CustomerVO(username, password), resStringRef);
		System.out.println(post);
		return post;
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
	void getUserInfo(){
		ResponseEntity<Result<String>> stringResult = login("15803012301","a123451");
		String token = stringResult.getBody().getResult();
		ResponseEntity<Result<CustomerVO>> post = getUserInfo(token);
		System.out.println(post);
		assertEquals(200, post.getBody().getCode());
		CustomerVO result1 = post.getBody().getResult();
		System.out.println(result1);
		assertEquals("15803012301", result1.getUsername());
	}

	ResponseEntity<Result<CustomerVO>> getUserInfo(String token){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add( HttpHeaders.AUTHORIZATION, "Bearer "+token);
	 	return helper.builder().setUipath("/user").build().get(httpHeaders,cusRef,null);
	}


	@Test
	void signup(){

		ResponseEntity<Result<CustomerVO>> post =
				helper.builder().setUipath("/user/signup").build().post(cusRef);
		System.out.println(post);

		assertEquals(400, post.getStatusCodeValue());
		assertEquals("请求异常", post.getBody().getMsg());
		assertTrue(post.getBody().getMsgDetail().contains("Required request body is missing"));

		ResponseEntity<Result<CustomerVO>> empty =
				helper.builder().setUipath("/user/signup").build().post(new CustomerVO(),cusRef);
		System.out.println(empty);
		assertEquals(400, empty.getStatusCodeValue());
		assertEquals("请求异常",  empty.getBody().getMsg());
		assertTrue( empty.getBody().getMsgDetail().split(",").length == 5);

		CustomerVO customerVO = new CustomerVO();
		customerVO.setAlias("rose2");
		customerVO.setPassword("a123321");
		customerVO.setEmail("a_331977552@qq.com");
		customerVO.setDateOfBirth(LocalDateTime.now());
		customerVO.setUsername("AAAAAD");
		customerVO.setPhone("130953245");
		ResponseEntity<Result<CustomerVO>> error =
				helper.builder().setUipath("/user/signup").build().post(customerVO,cusRef);
		System.out.println(error);
		assertEquals(400, error.getStatusCodeValue());
		assertEquals("请求异常",  error.getBody().getMsg());
		assertTrue( error.getBody().getMsgDetail() .equals("手机长度不正确"));
		customerVO.setPhone("13095324542");
		ResponseEntity<Result<CustomerVO>> success =
				helper.builder().setUipath("/user/signup").build().post(customerVO,cusRef);
		System.out.println(success);
		if(success.getStatusCodeValue() == 400 && success.getBody().getMsgDetail().equals("用户已存在")){
			ResponseEntity<Result<String>> login = this.login("AAAAAD", "a123321");
			ResponseEntity<Result<CustomerVO>> userInfo = getUserInfo(login.getBody().getResult());
			String id = userInfo.getBody().getResult().getId();
			customerDAOMapper.deleteByPrimaryKey(id);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			success = helper.builder().setUipath("/user/signup").build().post(customerVO,cusRef);
			System.out.println(success);
		}

		assertEquals(200, success.getStatusCodeValue());
		assertEquals("成功",  success.getBody().getMsg());
		assertTrue( success.getBody().getResult().getId().length() == 32);
		int i = customerDAOMapper.deleteByPrimaryKey(success.getBody().getResult().getId());
		System.out.println(i);
	}


}
