package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.UserRoleRequest;
import com.smart.erp.security.dto.response.UserRoleResponse;
import com.smart.erp.security.service.UserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserRoleResponse>>> getAllUserRoles() {
        return ResponseEntity.ok(ApiResponse.success(userRoleService.getAllUserRoles()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<UserRoleResponse>>> getUserRolesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(userRoleService.getUserRolesByUserId(userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserRoleResponse>> getUserRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(userRoleService.getUserRoleById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserRoleResponse>> createUserRole(@Valid @RequestBody UserRoleRequest request) {
        return ResponseEntity.ok(ApiResponse.success("User role assigned successfully", userRoleService.createUserRole(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserRoleResponse>> updateUserRole(
            @PathVariable Long id,
            @Valid @RequestBody UserRoleRequest request) {
        return ResponseEntity.ok(ApiResponse.success("User role updated successfully", userRoleService.updateUserRole(id, request)));
    }
}