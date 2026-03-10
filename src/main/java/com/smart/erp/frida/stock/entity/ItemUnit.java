package com.smart.erp.frida.stock.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "FSTK_ITEM_UNITS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemUnit extends BaseEntity {

    @Id
    @Column(name = "UNIT_CODE", precision = 2)
    @NotNull(message = "Unit code is required")
    private Integer unitCode;

    @Column(name = "UNIT_NAME_EN", length = 20, nullable = false)
    @NotBlank(message = "Unit name (EN) is required")
    @Size(max = 20, message = "Unit name (EN) must not exceed 20 characters")
    private String unitNameEn;

    @Column(name = "UNIT_NAME_AR", length = 20)
    @Size(max = 20, message = "Unit name (AR) must not exceed 20 characters")
    private String unitNameAr;
}
