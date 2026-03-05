package com.smart.erp.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Auditable {

    @Column(name = "CREATED_BY", nullable = false)
    private Long createdBy = -1L;

    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private Long lastUpdatedBy = -1L;

    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private LocalDateTime lastUpdateDate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        lastUpdateDate = LocalDateTime.now();
        if (createdBy == null) createdBy = -1L;
        if (lastUpdatedBy == null) lastUpdatedBy = -1L;
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdateDate = LocalDateTime.now();
        if (lastUpdatedBy == null) lastUpdatedBy = -1L;
    }
}