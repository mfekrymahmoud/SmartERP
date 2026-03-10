package com.smart.erp.frida.stock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
    name = "FSTK_ITEMS",
    indexes = {
        @Index(name = "FSTK_ITE_IT_FK_I", columnList = "ITEM_TYPE_CODE"),
        @Index(name = "FSTK_ITE_IU_FK_I", columnList = "UNIT_CODE"),
        @Index(name = "FSTK_ITM_NAME_I",  columnList = "ITEM_NAME_EN")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {

    @Id
    @Column(name = "ITEM_NUMBER", precision = 7)
    @NotNull(message = "Item number is required")
    private Integer itemNumber;

    @Column(name = "ITEM_NAME_EN", length = 100, nullable = false)
    @NotBlank(message = "Item name (EN) is required")
    @Size(max = 100, message = "Item name (EN) must not exceed 100 characters")
    private String itemNameEn;

    @Column(name = "ITEM_NAME_AR", length = 100)
    @Size(max = 100, message = "Item name (AR) must not exceed 100 characters")
    private String itemNameAr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "UNIT_CODE",
        referencedColumnName = "UNIT_CODE",
        foreignKey = @ForeignKey(name = "FSTK_ITE_IU_FK"),
        nullable = false
    )
    @NotNull(message = "Unit is required")
    private ItemUnit unit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "ITEM_TYPE_CODE",
        referencedColumnName = "ITEM_TYPE_CODE",
        foreignKey = @ForeignKey(name = "FSTK_ITE_IT_FK"),
        nullable = false
    )
    @NotNull(message = "Item type is required")
    private ItemType itemType;

    @Column(name = "ITEM_PRICE", precision = 9, scale = 2, nullable = false)
    @NotNull(message = "Item price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Item price must be greater than zero")
    @Digits(integer = 7, fraction = 2, message = "Item price format is invalid")
    private BigDecimal itemPrice;

    @Column(name = "ROBOT_NOZZLE_CODE", length = 60)
    @Size(max = 60, message = "Robot nozzle code must not exceed 60 characters")
    private String robotNozzleCode;
}
