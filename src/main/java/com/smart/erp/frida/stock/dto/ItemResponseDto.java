package com.smart.erp.frida.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Response DTO for an Item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDto {

    private Integer itemNumber;
    private String itemNameEn;
    private String itemNameAr;

    // flattened lookup fields
    private Integer unitCode;
    private String unitNameEn;

    private Integer itemTypeCode;
    private String itemTypeNameEn;

    private BigDecimal itemPrice;
    private String robotNozzleCode;

    private Long creationBy;
    private LocalDateTime creationDate;
    private Long lastUpdateBy;
    private LocalDateTime lastUpdateDate;
}
