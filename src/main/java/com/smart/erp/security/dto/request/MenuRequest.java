package com.smart.erp.security.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MenuRequest {
    private Long menuId;

    @NotBlank(message = "Menu code is required")
    private String menuCode;

    private String menuDescriptionAr;
    private String menuDescriptionEn;
    private Long parentMenuId;
    private Integer menuOrder = 0;
    private String icon;
    private String routePath;
}