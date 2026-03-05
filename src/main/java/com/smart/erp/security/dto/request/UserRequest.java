package com.smart.erp.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRequest {
    private Long userId;

    @NotBlank(message = "Username is required")
    private String userName;

    private String password;
    private Integer expirationMinutes = 30;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String languagePreference = "EN";
}