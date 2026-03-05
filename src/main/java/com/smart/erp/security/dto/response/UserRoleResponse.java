package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserRoleResponse {
    private Long userRoleId;
    private Long userId;
    private String userName;
    private Long roleId;
    private String roleDescriptionEn;
    private String roleDescriptionAr;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String isActive;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}