package com.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/*
 * @EnableJpaAuditing: activates Spring's auditing infrastructure.
 * auditorAwareRef = "auditorProvider": Spring looks for a bean named
 * "auditorProvider" and calls getCurrentAuditor() before every INSERT/UPDATE
 * to know which user is making the change.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        /*
         * In a real Spring Security application, you'd get the logged-in user:
         *
         * return () -> Optional.ofNullable(
         *     SecurityContextHolder.getContext().getAuthentication()
         * ).map(Authentication::getName);
         *
         * For this demo, we always return "system".
         * Every record will have created_by = "system"
         */
        return () -> Optional.of("system");
    }
}