package com.dgliger.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.ofNullable(userName);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return Optional.of("admin");
    }

}