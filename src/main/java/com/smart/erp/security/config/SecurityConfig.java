package com.smart.erp.security.config;

import com.smart.erp.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                // Auth & public endpoints
                                "/auth/**",
                                "/public/**",

                                // FIX: Spring Security evaluates paths WITHOUT the context-path prefix.
                                // So even though the browser hits /api/v1/api-docs, Security sees /api-docs.
                                // Match the springdoc.api-docs.path and swagger-ui.path values exactly.
                                "/api-docs",
                                "/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui/index.html",
                                "/swagger-ui.html",     // legacy redirect still works
                                "/webjars/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // FIX: Replaced the HttpSecurity-based AuthenticationManager bean with the
    // standard AuthenticationConfiguration approach.
    //
    // The original approach (http.getSharedObject(AuthenticationManagerBuilder.class))
    // can cause a circular dependency or double-build issue in Spring Security 6.x
    // because HttpSecurity already builds its own AuthenticationManager internally.
    // Using AuthenticationConfiguration is the recommended Spring Boot 3 pattern.
    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    // FIX: Declare AuthenticationProvider as a separate @Bean so Spring Security
    // auto-discovers and wires it into its internal AuthenticationManager.
    // This replaces the manual authenticationManagerBuilder.authenticationProvider(...)
    // call that was in the old authenticationManager() method.
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}