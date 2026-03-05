package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long userId;
    private String userName;
    private Integer expirationMinutes;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String languagePreference;
    private String isActive;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}