package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.LoginRequest;
import com.smart.erp.security.dto.response.JwtResponse;
import com.smart.erp.security.model.User;
import com.smart.erp.security.model.UserAudit;
import com.smart.erp.security.repository.UserAuditRepository;
import com.smart.erp.security.repository.UserRepository;
import com.smart.erp.security.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserAuditRepository userAuditRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletRequest httpRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUserName(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String jwt = jwtService.generateToken(userDetails, user);

        // Audit login
        UserAudit audit = new UserAudit();
        audit.setUserId(user.getUserId());
        audit.setLoginDateTime(LocalDateTime.now());
        audit.setIpAddress(getClientIp(httpRequest));
        audit.setSessionToken(jwt);
        userAuditRepository.save(audit);

        JwtResponse response = JwtResponse.builder()
                .token(jwt)
                .userId(user.getUserId())
                .userName(user.getUserName())
                .languagePreference(user.getLanguagePreference())
                .expiresIn(jwtService.getExpirationTime(user))
                .build();

        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @Transactional
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        // Guard: return 400 if header is missing or malformed instead of NullPointerException
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.error( "Missing or invalid Authorization header",400));
        }

        // Extract username and find audit record
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // Update logout time in audit table
        userAuditRepository.findLastActiveSession(user.getUserId()).ifPresent(audit ->
                userAuditRepository.updateLogoutTime(audit.getAuditId(), LocalDateTime.now())
        );

        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}