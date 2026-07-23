package com.cognizant.springlearn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AuthenticationController.class);

    // GET /authenticate
    // Spring reads Authorization header and passes it as authHeader
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("Start");
        LOGGER.debug("Authorization header received: {}", authHeader);

        // Step 1: extract username from the Basic auth header
        String user = getUser(authHeader);
        LOGGER.debug("Extracted user: {}", user);

        // Step 2: generate JWT for this user
        String token = generateJwt(user);
        LOGGER.debug("Generated token: {}", token);

        // Step 3: return token in response map
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("End");
        return map;
    }

    // Decode "Basic <base64(user:password)>" and return the username
    private String getUser(String authHeader) {
        LOGGER.info("Start");

        // Remove "Basic " prefix — everything after is Base64 encoded
        String encodedCredentials = authHeader.substring("Basic ".length());
        LOGGER.debug("Encoded credentials: {}", encodedCredentials);

        // Decode Base64 → "user:pwd"
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes);
        LOGGER.debug("Decoded credentials: {}", decodedString);

        // Split on ":" → ["user", "pwd"] → return "user"
        String user = decodedString.substring(0, decodedString.indexOf(":"));
        LOGGER.debug("User: {}", user);

        LOGGER.info("End");
        return user;
    }

    // Generate a JWT token for the given username
    private String generateJwt(String user) {
        LOGGER.info("Start");

        JwtBuilder builder = Jwts.builder();

        // Subject: the username stored in the token
        builder.setSubject(user);

        // Issued at: current timestamp
        builder.setIssuedAt(new Date());

        // Expiration: 20 minutes from now (1200000 ms)
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));

        // Sign with HMAC SHA-256 using "secretkey"
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();
        LOGGER.debug("JWT: {}", token);
        LOGGER.info("End");

        return token;
    }
}