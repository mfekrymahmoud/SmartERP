package com.smart.erp.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FunctionRequest {
    private Long functionId;

    @NotBlank(message = "Function code is required")
    private String functionCode;

    private String functionDescriptionAr;
    private String functionDescriptionEn;
}