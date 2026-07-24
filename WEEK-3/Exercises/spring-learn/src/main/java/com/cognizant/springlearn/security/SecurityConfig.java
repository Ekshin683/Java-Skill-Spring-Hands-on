package com.cognizant.springlearn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SecurityConfig.class);
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        LOGGER.info("Start");
        auth.inMemoryAuthentication()
            .withUser("admin")
                .password(passwordEncoder().encode("pwd"))
                .roles("ADMIN")
            .and()
            .withUser("user")
                .password(passwordEncoder().encode("pwd"))
                .roles("USER");
        LOGGER.info("End");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        LOGGER.info("Start");
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        LOGGER.info("Start");
        httpSecurity
            .csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
                // /authenticate accessible by both USER and ADMIN
                .antMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                // all other requests need authentication
                .anyRequest().authenticated()
            .and()
            // Add JWT filter — validates Bearer token on every request
            .addFilter(new JwtAuthorizationFilter(authenticationManager()));
        LOGGER.info("End");
    }
}