package org.shop;

import org.junit.jupiter.api.Test;
import org.shop.common.RedisService;
import org.shop.common.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GeneralTests {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	RedisService redisService;

	@Test
	void testJWTUtils(){
		String s = jwtTokenUtil.generateToken("123");
		System.out.println(s);
		String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlYTYxOWY0MzQzZDA0MTA3OTRmNGQ1NTJmMDQ2Y2Q5MSIsImlhdCI6MTYyMDg4NjM4MSwiZXhwIjoxNjIwODg2Mzg3fQ.omwReAulq_j7erZLxpLoeYKArdm1QJqM9iuLgzozkFM2aIqR0B24AuR7Bn3FFdwfyI5fSQD6yNzVhFJajAPgxA";
		JwtTokenUtil.JwtValidation validate = jwtTokenUtil.validate(token);
		System.out.println(validate);
		boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
		System.out.println(tokenExpired);
		String customerVO = jwtTokenUtil.parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2MjA4ODQ0NTQsImV4cCI6MTYyMDg4NDQ2MH0.IkhWq5FeRL6kx_DJSBKYBf4iV1wsVbqNDSC6Pv-ZuoNUFWRdIThAekqdL9IIfha44-rgNHaBIcUA1YYM0WmTwg");
		System.out.println(customerVO);
	}
	@Test
	void testRedis(){

		Object o = redisService.get("15803012301");

		System.out.println(o);
	}




}
