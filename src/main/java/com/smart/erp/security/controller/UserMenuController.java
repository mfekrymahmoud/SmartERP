package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.response.UserMenuResponse;
import com.smart.erp.security.service.UserMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-menus")
@RequiredArgsConstructor
public class UserMenuController {

    private final UserMenuService userMenuService;

    /**
     * Get all menus for a specific user (flat list)
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getUserMenus(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getUserMenus(userId)));
    }

    /**
     * Get root menus for a specific user (parent menus only)
     */
    @GetMapping("/{userId}/root")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getRootMenus(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getRootMenus(userId)));
    }

    /**
     * Get sub-menus for a specific parent menu
     */
    @GetMapping("/{userId}/children/{parentId}")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getSubMenus(
            @PathVariable Long userId,
            @PathVariable Long parentId) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getSubMenus(userId, parentId)));
    }

    /**
     * Get menus by access type (RO = Read Only, RW = Read Write)
     */
    @GetMapping("/{userId}/access/{accessType}")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getMenusByAccessType(
            @PathVariable Long userId,
            @PathVariable String accessType) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getMenusByAccessType(userId, accessType)));
    }

    /**
     * Get current user's menus (from JWT token)
     */
    @GetMapping("/current")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getCurrentUserMenus(
            @RequestHeader("Authorization") String token) {
        // Extract userId from token or SecurityContext
        Long userId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getUserMenus(userId)));
    }

    /**
     * Get current user's menus in tree structure (hierarchical)
     */
    @GetMapping("/current/tree")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getCurrentUserMenuTree(
            @RequestHeader("Authorization") String token) {
        Long userId = extractUserIdFromToken(token);
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getMenuTree(userId)));
    }

    /**
     * Get menu tree for specific user (hierarchical structure)
     */
    @GetMapping("/{userId}/tree")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getUserMenuTree(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getMenuTree(userId)));
    }

    /**
     * Check if user has access to specific function
     */
    @GetMapping("/{userId}/check-access")
    public ResponseEntity<ApiResponse<Boolean>> checkFunctionAccess(
            @PathVariable Long userId,
            @RequestParam String functionCode) {
        boolean hasAccess = userMenuService.hasFunctionAccess(userId, functionCode);
        return ResponseEntity.ok(ApiResponse.success(hasAccess));
    }

    /**
     * Get user menu with specific language
     */
    @GetMapping("/{userId}/lang/{language}")
    public ResponseEntity<ApiResponse<List<UserMenuResponse>>> getUserMenusByLanguage(
            @PathVariable Long userId,
            @PathVariable String language) {
        return ResponseEntity.ok(ApiResponse.success(userMenuService.getUserMenusByLanguage(userId, language)));
    }

    // Helper method to extract userId from JWT token
    private Long extractUserIdFromToken(String token) {
        // Implement token parsing logic here
        // This is a placeholder - use your JwtService to extract userId
        return 1L; // Replace with actual implementation
    }
}