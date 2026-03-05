package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MenuDetailResponse {
    private Long menuDetailId;
    private Long menuId;
    private Long functionId;
    private String functionCode;
    private String functionDescEn;
    private String functionDescAr;
    private Long subMenuId;
    private String subMenuDescEn;
    private String subMenuDescAr;
    private String accessType;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}