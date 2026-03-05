package com.smart.erp.security.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "FND_USERS")
@EqualsAndHashCode(callSuper = true)
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "FND_USERS_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME", nullable = false, unique = true, length = 100)
    private String userName;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false, length = 255)
    private String encryptedPassword;

    @Column(name = "EXPIRATION_MINUTES")
    private Integer expirationMinutes = 30;

    @Column(name = "EFFECTIVE_START_DATE")
    private LocalDate effectiveStartDate;

    @Column(name = "EFFECTIVE_END_DATE")
    private LocalDate effectiveEndDate;

    @Column(name = "LANGUAGE_PREFERENCE", length = 2)
    private String languagePreference = "EN";

    public boolean isActive() {
        LocalDate now = LocalDate.now();
        return (effectiveEndDate == null || effectiveEndDate.isAfter(now))
                && (effectiveStartDate == null || !effectiveStartDate.isAfter(now));
    }
}