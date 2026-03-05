package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "FND_USER_ROLES")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_seq")
    @SequenceGenerator(name = "user_role_seq", sequenceName = "FND_USER_ROLES_SEQ", allocationSize = 1)
    @Column(name = "USER_ROLE_ID")
    private Long userRoleId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "ROLE_ID", nullable = false)
    private Long roleId;

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