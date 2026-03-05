package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.MenuRequest;
import com.smart.erp.security.dto.response.MenuResponse;
import com.smart.erp.security.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuResponse>>> getAllMenus() {
        return ResponseEntity.ok(ApiResponse.success(menuService.getAllMenus()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponse>> getMenuById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(menuService.getMenuById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MenuResponse>> createMenu(@Valid @RequestBody MenuRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Menu created successfully", menuService.createMenu(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponse>> updateMenu(
            @PathVariable Long id,
            @Valid @RequestBody MenuRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Menu updated successfully", menuService.updateMenu(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMenu(@PathVariable Long id) {
        menuService.deleteMenu(id);
        return ResponseEntity.ok(ApiResponse.success("Menu deleted successfully", null));
    }
}