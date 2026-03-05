package com.smart.erp.security.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class RoleRequest {
    private Long roleId;
    private String roleDescriptionAr;
    private String roleDescriptionEn;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
}