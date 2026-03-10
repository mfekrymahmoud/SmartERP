package com.smart.erp.frida.stock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "FSTK_ITEM_TYPES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemType extends BaseEntity {

    @Id
    @Column(name = "ITEM_TYPE_CODE", precision = 2)
    @NotNull(message = "Item type code is required")
    private Integer itemTypeCode;

    @Column(name = "ITEM_TYPE_NAME_EN", length = 50, nullable = false)
    @NotBlank(message = "Item type name (EN) is required")
    @Size(max = 50, message = "Item type name (EN) must not exceed 50 characters")
    private String itemTypeNameEn;

    @Column(name = "ITEM_TYPE_NAME_AR", length = 50)
    @Size(max = 50, message = "Item type name (AR) must not exceed 50 characters")
    private String itemTypeNameAr;
}
