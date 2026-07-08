package com.cognizant.springlearn.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

	private static final String SECRET = "my-secret-key-for-jwt-token-generation-needs-to-be-at-least-256-bits";
	private static final long EXPIRATION_TIME = 1200000; // 20 minutes in milliseconds

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(SECRET.getBytes());
	}

	public String generateToken(String username) {
		LOGGER.info("Start of generateToken() method. username={}", username);
		Map<String, Object> claims = new HashMap<>();
		String token = Jwts.builder()
				.claims(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSigningKey())
				.compact();
		LOGGER.info("End of generateToken() method");
		return token;
	}

}
