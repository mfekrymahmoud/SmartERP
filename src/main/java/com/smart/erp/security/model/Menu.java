package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "FND_MENUS")
@EqualsAndHashCode(callSuper = true)
public class Menu extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @SequenceGenerator(name = "menu_seq", sequenceName = "FND_MENUS_SEQ", allocationSize = 1)
    @Column(name = "MENU_ID")
    private Long menuId;

    @Column(name = "MENU_DESCRIPTION_AR", length = 200)
    private String menuDescriptionAr;

    @Column(name = "MENU_DESCRIPTION_EN", length = 200)
    private String menuDescriptionEn;

    @Column(name = "MENU_CODE", unique = true, length = 50)
    private String menuCode;

    @Column(name = "PARENT_MENU_ID")
    private Long parentMenuId;

    @Column(name = "MENU_ORDER")
    private Integer menuOrder = 0;

    @Column(name = "ICON", length = 50)
    private String icon;

    @Column(name = "ROUTE_PATH", length = 100)
    private String routePath;
}