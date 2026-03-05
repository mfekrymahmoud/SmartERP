package com.smart.erp.security.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MenuDetailRequest {
    private Long menuDetailId;

    @NotNull(message = "Menu ID is required")
    private Long menuId;

    private Long functionId;
    private Long subMenuId;
    private String accessType; // RO or RW
}