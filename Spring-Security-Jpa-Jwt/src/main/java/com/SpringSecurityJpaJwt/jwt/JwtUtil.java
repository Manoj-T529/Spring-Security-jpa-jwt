package com.SpringSecurityJpaJwt.jwt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource(value = { "classpath:application.yml" })
public class JwtUtil {

	@Value("${security.jwt.secret:secret}")
	private String secret;

	public boolean validateToken(String token, String username) {
		String tokenUsername = getUsername(token);

		return (username.equals(tokenUsername) && !isTokenExp(token));
	}

	public boolean isTokenExp(String token) {
		Date ExpDate = getExp(token);
		return ExpDate.before(new Date(System.currentTimeMillis()));
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();
	}

	public Date getExp(String token) {
		return getClaims(token).getExpiration();
	}

	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	public String generateToken(String subject) {
		return Jwts.builder().setSubject(subject).setIssuer("ManojT").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

}
