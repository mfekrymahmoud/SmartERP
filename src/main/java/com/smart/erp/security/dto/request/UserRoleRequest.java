package com.smart.erp.security.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserRoleRequest {
    private Long userRoleId;
    private Long userId;
    private Long roleId;
    private LocalDate effectiveStartDate;
    private LocalDate effectiveEndDate;
}