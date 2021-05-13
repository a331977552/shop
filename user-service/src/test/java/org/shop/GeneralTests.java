package org.shop;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.shop.model.vo.CustomerVO;
import org.shop.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Set;

@SpringBootTest
public class GeneralTests {

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Test
	void testJWTUtils(){
		String s = jwtTokenUtil.generateToken("123");
		System.out.println(s);
		String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJlYTYxOWY0MzQzZDA0MTA3OTRmNGQ1NTJmMDQ2Y2Q5MSIsImlhdCI6MTYyMDg4NjM4MSwiZXhwIjoxNjIwODg2Mzg3fQ.omwReAulq_j7erZLxpLoeYKArdm1QJqM9iuLgzozkFM2aIqR0B24AuR7Bn3FFdwfyI5fSQD6yNzVhFJajAPgxA";
		JwtTokenUtil.JwtValidation validate = jwtTokenUtil.validate(token);
		System.out.println(validate);
		boolean tokenExpired = jwtTokenUtil.isTokenExpired(token);
		System.out.println(tokenExpired);
		CustomerVO customerVO = jwtTokenUtil.parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMjMiLCJpYXQiOjE2MjA4ODQ0NTQsImV4cCI6MTYyMDg4NDQ2MH0.IkhWq5FeRL6kx_DJSBKYBf4iV1wsVbqNDSC6Pv-ZuoNUFWRdIThAekqdL9IIfha44-rgNHaBIcUA1YYM0WmTwg");
		System.out.println(customerVO);

	}

}
