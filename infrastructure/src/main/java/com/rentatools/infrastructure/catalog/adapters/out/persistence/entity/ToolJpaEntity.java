package com.rentatools.infrastructure.catalog.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/** Entidad JPA de la tabla {@code tools}. Solo vive en infraestructura. */
@Entity
@Table(name = "tools")
public class ToolJpaEntity {

    @Id
    private UUID id;

    @Column(name = "provider_id", nullable = false)
    private UUID providerId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 60)
    private String brand;

    @Column(nullable = false, length = 60)
    private String model;

    @Column(name = "daily_rental_rate", nullable = false, precision = 12, scale = 2)
    private BigDecimal dailyRentalRate;

    @Column(name = "security_deposit_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal securityDepositAmount;

    @Column(name = "replacement_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal replacementValue;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected ToolJpaEntity() {
        // Requerido por JPA.
    }

    public ToolJpaEntity(UUID id, UUID providerId, UUID categoryId, String code, String name,
                         String description, String brand, String model, BigDecimal dailyRentalRate,
                         BigDecimal securityDepositAmount, BigDecimal replacementValue, boolean active,
                         Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.providerId = providerId;
        this.categoryId = categoryId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.model = model;
        this.dailyRentalRate = dailyRentalRate;
        this.securityDepositAmount = securityDepositAmount;
        this.replacementValue = replacementValue;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public UUID getProviderId() { return providerId; }
    public UUID getCategoryId() { return categoryId; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public BigDecimal getDailyRentalRate() { return dailyRentalRate; }
    public BigDecimal getSecurityDepositAmount() { return securityDepositAmount; }
    public BigDecimal getReplacementValue() { return replacementValue; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
