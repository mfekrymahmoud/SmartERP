package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.response.UserMenuResponse;
import com.smart.erp.security.service.UserMenuService;
import com.smart.erp.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user-menus")
@RequiredArgsConstructor
public class UserMenuController {

    private final UserMenuService userMenuService;
    private final JwtService jwtService;

    @GetMapping("/current/tree")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getCurrentUserMenuTree(
            @RequestHeader("Authorization") String token) {
        // Advice: Extract dynamic userId from JWT instead of hardcoded 1L
        Long userId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getMenuTree(userId)));
    }

    private Long extractUserIdFromToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            return jwtService.extractClaim(jwt, claims -> claims.get("userId", Long.class));
        }
        throw new RuntimeException("Invalid Authorization Header");
    }
}