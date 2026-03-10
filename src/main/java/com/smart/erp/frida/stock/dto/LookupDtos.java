package com.smart.erp.frida.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

// ─── Item Unit ────────────────────────────────────────────────────────────────

@Schema(description = "Request DTO for creating/updating an Item Unit")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class ItemUnitRequestDto {
    @NotNull(message = "Unit code is required")
    @Schema(description = "Unique unit code (max 2 digits)", example = "1")
    private Integer unitCode;

    @NotBlank(message = "Unit name (EN) is required")
    @Size(max = 20)
    @Schema(description = "Unit name in English", example = "Piece")
    private String unitNameEn;

    @Size(max = 20)
    @Schema(description = "Unit name in Arabic", example = "قطعة")
    private String unitNameAr;
}

@Schema(description = "Response DTO for an Item Unit")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class ItemUnitResponseDto {
    private Integer unitCode;
    private String unitNameEn;
    private String unitNameAr;
    private Long creationBy;
    private LocalDateTime creationDate;
    private Long lastUpdateBy;
    private LocalDateTime lastUpdateDate;
}

// ─── Item Type ────────────────────────────────────────────────────────────────

@Schema(description = "Request DTO for creating/updating an Item Type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class ItemTypeRequestDto {
    @NotNull(message = "Item type code is required")
    @Schema(description = "Unique item type code (max 2 digits)", example = "1")
    private Integer itemTypeCode;

    @NotBlank(message = "Item type name (EN) is required")
    @Size(max = 50)
    @Schema(description = "Item type name in English", example = "Raw Material")
    private String itemTypeNameEn;

    @Size(max = 50)
    @Schema(description = "Item type name in Arabic", example = "مادة خام")
    private String itemTypeNameAr;
}

@Schema(description = "Response DTO for an Item Type")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
class ItemTypeResponseDto {
    private Integer itemTypeCode;
    private String itemTypeNameEn;
    private String itemTypeNameAr;
    private Long creationBy;
    private LocalDateTime creationDate;
    private Long lastUpdateBy;
    private LocalDateTime lastUpdateDate;
}
