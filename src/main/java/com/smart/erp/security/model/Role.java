package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "FND_ROLES")
@EqualsAndHashCode(callSuper = true)
public class Role extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "FND_ROLES_SEQ", allocationSize = 1)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ROLE_DESCRIPTION_AR", length = 200)
    private String roleDescriptionAr;

    @Column(name = "ROLE_DESCRIPTION_EN", length = 200)
    private String roleDescriptionEn;

    @Column(name = "EFFECTIVE_START_DATE")
    private LocalDate effectiveStartDate;

    @Column(name = "EFFECTIVE_END_DATE")
    private LocalDate effectiveEndDate;

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return (effectiveEndDate == null || effectiveEndDate.isAfter(now))
                && (effectiveStartDate == null || !effectiveStartDate.isAfter(now));
    }
}