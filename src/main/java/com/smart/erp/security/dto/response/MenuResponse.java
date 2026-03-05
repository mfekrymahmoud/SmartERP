package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MenuResponse {
    private Long menuId;
    private String menuCode;
    private String menuDescriptionAr;
    private String menuDescriptionEn;
    private Long parentMenuId;
    private Integer menuOrder;
    private String icon;
    private String routePath;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}