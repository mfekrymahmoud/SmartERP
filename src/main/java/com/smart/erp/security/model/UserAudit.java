package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "FND_USER_AUDIT")
public class UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
    @SequenceGenerator(name = "audit_seq", sequenceName = "FND_USER_AUDIT_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_ID")
    private Long auditId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "LOGIN_DATE_TIME")
    private LocalDateTime loginDateTime;

    @Column(name = "LOGOUT_DATE_TIME")
    private LocalDateTime logoutDateTime;

    @Column(name = "IP_ADDRESS", length = 50)
    private String ipAddress;

    @Column(name = "SESSION_TOKEN", length = 500)
    private String sessionToken;
}