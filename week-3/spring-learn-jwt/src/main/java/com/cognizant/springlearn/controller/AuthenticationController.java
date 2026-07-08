package com.cognizant.springlearn.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.util.JwtUtil;

@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private JwtUtil jwtUtil;

	// Hardcoded credentials for demonstration
	private static final String VALID_USERNAME = "user";
	private static final String VALID_PASSWORD = "pwd";

	@GetMapping("/authenticate")
	public ResponseEntity<Map<String, String>> authenticate(
			@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		LOGGER.info("Start of authenticate() method");

		// Check if Authorization header is present
		if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
			LOGGER.warn("Missing or invalid Authorization header");
			Map<String, String> error = new HashMap<>();
			error.put("error", "Authorization header is required. Use: curl -u user:pwd http://localhost:8090/authenticate");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}

		// Step 1: Read Authorization header and decode username and password
		// The -u option in curl sends credentials as Basic Auth: "Basic base64(user:pwd)"
		String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
		String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
		String[] parts = credentials.split(":", 2);
		String username = parts[0];
		String password = parts[1];

		LOGGER.debug("Decoded username: {}", username);

		// Step 2: Validate credentials
		if (!VALID_USERNAME.equals(username) || !VALID_PASSWORD.equals(password)) {
			LOGGER.warn("Invalid credentials for user: {}", username);
			Map<String, String> error = new HashMap<>();
			error.put("error", "Invalid credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
		}

		// Step 3: Generate token based on the user
		String token = jwtUtil.generateToken(username);
		LOGGER.debug("Generated token: {}", token);

		Map<String, String> response = new HashMap<>();
		response.put("token", token);

		LOGGER.info("End of authenticate() method");
		return ResponseEntity.ok(response);
	}

}
