package com.smart.erp.security.controller;

import com.smart.erp.security.dto.ApiResponse;
import com.smart.erp.security.dto.request.FunctionRequest;
import com.smart.erp.security.dto.response.FunctionResponse;
import com.smart.erp.security.service.FunctionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/functions")
@RequiredArgsConstructor
public class FunctionController {

    private final FunctionService functionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<FunctionResponse>>> getAllFunctions() {
        return ResponseEntity.ok(ApiResponse.success(functionService.getAllFunctions()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FunctionResponse>> getFunctionById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(functionService.getFunctionById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FunctionResponse>> createFunction(@Valid @RequestBody FunctionRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Function created successfully", functionService.createFunction(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FunctionResponse>> updateFunction(
            @PathVariable Long id,
            @Valid @RequestBody FunctionRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Function updated successfully", functionService.updateFunction(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteFunction(@PathVariable Long id) {
        functionService.deleteFunction(id);
        return ResponseEntity.ok(ApiResponse.success("Function deleted successfully", null));
    }
}