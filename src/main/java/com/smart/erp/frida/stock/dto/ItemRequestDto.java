package com.smart.erp.frida.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Request DTO for creating/updating an Item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestDto {

    @NotNull(message = "Item number is required")
    @Schema(description = "Unique item number (max 7 digits)", example = "1000001")
    private Integer itemNumber;

    @NotBlank(message = "Item name (EN) is required")
    @Size(max = 100)
    @Schema(description = "Item name in English", example = "Steel Bolt M8")
    private String itemNameEn;

    @Size(max = 100)
    @Schema(description = "Item name in Arabic", example = "برغي فولاذ M8")
    private String itemNameAr;

    @NotNull(message = "Unit code is required")
    @Schema(description = "FK to FSTK_ITEM_UNITS", example = "1")
    private Integer unitCode;

    @NotNull(message = "Item type code is required")
    @Schema(description = "FK to FSTK_ITEM_TYPES", example = "2")
    private Integer itemTypeCode;

    @NotNull(message = "Item price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Digits(integer = 7, fraction = 2)
    @Schema(description = "Unit price", example = "12.50")
    private BigDecimal itemPrice;

    @Size(max = 60)
    @Schema(description = "Robot nozzle code (optional)", example = "NZL-001")
    private String robotNozzleCode;
}
