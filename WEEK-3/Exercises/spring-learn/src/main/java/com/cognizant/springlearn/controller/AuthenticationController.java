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

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(
            @RequestHeader("Authorization") String authHeader) {

        LOGGER.info("Start");
        LOGGER.debug("Authorization header received: {}", authHeader);
        String user = getUser(authHeader);
        LOGGER.debug("Extracted user: {}", user);
        String token = generateJwt(user);
        LOGGER.debug("Generated token: {}", token);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);

        LOGGER.info("End");
        return map;
    }
    private String getUser(String authHeader) {
        LOGGER.info("Start");
        String encodedCredentials = authHeader.substring("Basic ".length());
        LOGGER.debug("Encoded credentials: {}", encodedCredentials);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes);
        LOGGER.debug("Decoded credentials: {}", decodedString);
        String user = decodedString.substring(0, decodedString.indexOf(":"));
        LOGGER.debug("User: {}", user);

        LOGGER.info("End");
        return user;
    }
    private String generateJwt(String user) {
        LOGGER.info("Start");

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        builder.setIssuedAt(new Date());
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        String token = builder.compact();
        LOGGER.debug("JWT: {}", token);
        LOGGER.info("End");

        return token;
    }
}