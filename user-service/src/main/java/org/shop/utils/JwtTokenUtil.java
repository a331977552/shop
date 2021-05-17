package org.shop.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.shop.model.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

	public enum JwtValidation{
		SUCCESS,
		FAILED,
		EXPIRED
	}
	private final SecretKey secretKey;
	public JwtTokenUtil(@Value("${jwt.shop.secret}") String SECRET_KEY) {
		secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	@Value( "${jwt.expiration.time}" )
	private long expiration;
	public  Claims getClaim(String token){
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return body;
	}
	public  <T> T getClaim(String token, Function<Claims,T> fuc) throws ExpiredJwtException, SignatureException {
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return fuc.apply(body);
	}

	public Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	}

	public boolean isTokenExpired(String token) {
		try {
			return getExpiration(token).before(new Date());
		}catch (ExpiredJwtException e){
			return true;
		}
	}

	public String generateToken(String username) {
		 return Jwts.builder().setSubject(username).setIssuedAt(Date.from(Instant.now())).
				setExpiration(Date.from(Instant.now().plusMillis(expiration)))
				.signWith(secretKey, SignatureAlgorithm.HS512).compact();
	}


	public JwtValidation validate(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return JwtValidation.SUCCESS;
		}catch (ExpiredJwtException e)
		{
			return JwtValidation.EXPIRED;
		} catch (JwtException e){
			e.printStackTrace();
			return JwtValidation.FAILED;
		}

	}

	public CustomerVO parseToken(String token) {
		try {
			Jws<Claims> headerClaimsJwt = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			String subject = headerClaimsJwt.getBody().getSubject();
			CustomerVO u = new CustomerVO();
			u.setUsername(subject);
			return u;
		} catch (JwtException | ClassCastException e) {
			e.printStackTrace();
			return null;
		}
	}
}
