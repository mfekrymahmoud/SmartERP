package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "FND_MENU_DETAILS")
@EqualsAndHashCode(callSuper = true)
public class MenuDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_det_seq")
    @SequenceGenerator(name = "menu_det_seq", sequenceName = "FND_MENU_DETAILS_SEQ", allocationSize = 1)
    @Column(name = "MENU_DETAIL_ID")
    private Long menuDetailId;

    @Column(name = "MENU_ID", nullable = false)
    private Long menuId;

    @Column(name = "FUNCTION_ID")
    private Long functionId;

    @Column(name = "SUB_MENU_ID")
    private Long subMenuId;

    @Column(name = "ACCESS_TYPE", length = 2)
    private String accessType; // RO or RW
}