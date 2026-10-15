package com.rentatools.infrastructure.rental.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservations")
public class ReservationJpaEntity {

    @Id
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @Column(name = "reservation_number", nullable = false, unique = true, length = 50)
    private String reservationNumber;

    @Column(nullable = false, length = 25)
    private String status;

    @Column(name = "total_rental_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalRentalAmount;

    @Column(name = "total_deposit_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalDepositAmount;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "cancelled_reason", length = 255)
    private String cancelledReason;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    // Propiedades transitorias / columnas adicionales persistidas
    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "daily_rate", precision = 12, scale = 2)
    private BigDecimal dailyRate;

    @Column(name = "tool_id")
    private UUID toolId;

    protected ReservationJpaEntity() {
    }

    public ReservationJpaEntity(UUID id, UUID clientId, String reservationNumber, String status,
                                BigDecimal totalRentalAmount, BigDecimal totalDepositAmount,
                                Instant expiresAt, String cancelledReason,
                                LocalDate startDate, LocalDate endDate, BigDecimal dailyRate, UUID toolId,
                                Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.clientId = clientId;
        this.reservationNumber = reservationNumber;
        this.status = status;
        this.totalRentalAmount = totalRentalAmount;
        this.totalDepositAmount = totalDepositAmount;
        this.expiresAt = expiresAt;
        this.cancelledReason = cancelledReason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyRate = dailyRate;
        this.toolId = toolId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public UUID getClientId() { return clientId; }
    public String getReservationNumber() { return reservationNumber; }
    public String getStatus() { return status; }
    public BigDecimal getTotalRentalAmount() { return totalRentalAmount; }
    public BigDecimal getTotalDepositAmount() { return totalDepositAmount; }
    public Instant getExpiresAt() { return expiresAt; }
    public String getCancelledReason() { return cancelledReason; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public BigDecimal getDailyRate() { return dailyRate; }
    public UUID getToolId() { return toolId; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
