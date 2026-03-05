package com.smart.erp.security.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FunctionResponse {
    private Long functionId;
    private String functionCode;
    private String functionDescriptionAr;
    private String functionDescriptionEn;
    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}