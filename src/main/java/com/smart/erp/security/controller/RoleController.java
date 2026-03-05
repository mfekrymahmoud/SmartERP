package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.RoleRequest;
import com.smart.erp.security.dto.response.RoleResponse;
import com.smart.erp.security.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getAllRoles() {
        return ResponseEntity.ok(ApiResponse.success(roleService.getAllRoles()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(roleService.getRoleById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Role created successfully", roleService.createRole(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleResponse>> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Role updated successfully", roleService.updateRole(id, request)));
    }
}