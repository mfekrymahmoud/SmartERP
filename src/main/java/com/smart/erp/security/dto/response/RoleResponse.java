package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RoleResponse {
    private Long roleId;
    private String roleDescriptionAr;
    private String roleDescriptionEn;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
    private String isActive;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}