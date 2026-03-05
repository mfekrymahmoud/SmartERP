package com.smart.erp.security.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class SmartAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // Advice: This allows Auditable to automatically pick up the user ID from the context
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(auth -> auth.isAuthenticated() && !auth.getName().equals("anonymousUser"))
                .map(auth -> (Long) auth.getPrincipal());
    }
}