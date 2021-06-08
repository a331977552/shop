package org.shop.common.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Component
@PropertySource("classpath:common.properties")
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
	public Claims getClaim(String token){
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return body;
	}
	public  <T> T getClaim(String token, Function<Claims,T> fuc) throws ExpiredJwtException, SignatureException {
		Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
		Claims body = claimsJws.getBody();
		return fuc.apply(body);
	}

	public long getExpirationLength(){
		return expiration;
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

	public String parseToken(String token) {
			Jws<Claims> headerClaimsJwt = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
		return headerClaimsJwt.getBody().getSubject();
	}
}
