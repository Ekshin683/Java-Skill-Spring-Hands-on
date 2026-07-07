package com.employee.entity;

// @MappedSuperclass: this class is NOT its own table.
// Its fields are inherited (merged) into every subclass table.
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
// AuditingEntityListener is the Spring hook that auto-populates
// @CreatedDate / @LastModifiedDate / @CreatedBy / @LastModifiedBy
// before every INSERT and UPDATE operation
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    // @CreatedBy: Spring calls AuditorAware.getCurrentAuditor() and stores the result
    // updatable = false: once written, this column is never changed again
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    // @CreatedDate: Spring fills this with the current timestamp on INSERT
    // updatable = false: never overwritten on UPDATE
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // @LastModifiedBy: updated on every save (INSERT and UPDATE)
    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    // @LastModifiedDate: timestamp of most recent save
    @LastModifiedDate
    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;

    // Getters — no setters needed, Spring sets these values
    public String getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public String getLastModifiedBy() { return lastModifiedBy; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
}