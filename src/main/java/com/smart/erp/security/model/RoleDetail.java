package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "FND_ROLE_DETAILS")
@EqualsAndHashCode(callSuper = true)
public class RoleDetail extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_det_seq")
    @SequenceGenerator(name = "role_det_seq", sequenceName = "FND_ROLE_DETAILS_SEQ", allocationSize = 1)
    @Column(name = "ROLE_DETAIL_ID")
    private Long roleDetailId;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

    @Column(name = "MENU_DETAIL_ID", nullable = false)
    private Long menuDetailId;
}