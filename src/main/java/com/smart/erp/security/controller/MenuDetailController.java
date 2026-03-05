package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.MenuDetailRequest;
import com.smart.erp.security.dto.response.MenuDetailResponse;
import com.smart.erp.security.service.MenuDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu-details")
@RequiredArgsConstructor
public class MenuDetailController {

    private final MenuDetailService menuDetailService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuDetailResponse>>> getAllMenuDetails() {
        return ResponseEntity.ok(ApiResponse.success(menuDetailService.getAllMenuDetails()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuDetailResponse>> getMenuDetailById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(menuDetailService.getMenuDetailById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MenuDetailResponse>> createMenuDetail(@Valid @RequestBody MenuDetailRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Menu detail created successfully", menuDetailService.createMenuDetail(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuDetailResponse>> updateMenuDetail(
            @PathVariable Long id,
            @Valid @RequestBody MenuDetailRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Menu detail updated successfully", menuDetailService.updateMenuDetail(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenuDetail(@PathVariable Long id) {
        menuDetailService.deleteMenuDetail(id);
        return ResponseEntity.ok(ApiResponse.success("Menu detail deleted successfully", null));
    }
}