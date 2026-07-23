package com.cognizant.springlearn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// Extends BasicAuthenticationFilter so Spring wires it into the security chain
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        LOGGER.info("Start");
        LOGGER.debug("AuthenticationManager: {}", authenticationManager);
    }

    // Runs on every HTTP request that passes through the filter chain
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                     HttpServletResponse res,
                                     FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info("Start");

        String header = req.getHeader("Authorization");
        LOGGER.debug("Authorization header: {}", header);

        // If no Authorization header or not a Bearer token — skip JWT check
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        // Validate the JWT token and get authentication object
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        // Set authentication in Spring Security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);

        LOGGER.info("End");
    }

    // Validates the JWT token and returns an Authentication object
    private UsernamePasswordAuthenticationToken getAuthentication(
            HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token != null) {
            try {
                // Parse and validate the token using the secret key
                Jws<Claims> jws = Jwts.parser()
                        .setSigningKey("secretkey")
                        .parseClaimsJws(token.replace("Bearer ", ""));

                // Extract the username from the token subject
                String user = jws.getBody().getSubject();
                LOGGER.debug("JWT user: {}", user);

                if (user != null) {
                    // Return authenticated token with empty authorities
                    return new UsernamePasswordAuthenticationToken(
                            user, null, new ArrayList<>());
                }

            } catch (JwtException ex) {
                LOGGER.warn("JWT validation failed: {}", ex.getMessage());
                return null;
            }
        }
        return null;
    }
}