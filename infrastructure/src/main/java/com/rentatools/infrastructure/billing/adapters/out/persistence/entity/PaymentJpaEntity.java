package com.rentatools.infrastructure.billing.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class PaymentJpaEntity {

    @Id
    private UUID id;

    @Column(name = "reservation_id", nullable = false)
    private UUID reservationId;

    @Column(name = "payment_reference", nullable = false, unique = true, length = 60)
    private String paymentReference;

    @Column(name = "payment_method", nullable = false, length = 30)
    private String paymentMethod;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 25)
    private String status;

    @Column(name = "gateway_transaction_id", length = 100)
    private String gatewayTransactionId;

    @Column(name = "gateway_response_code", length = 50)
    private String gatewayResponseCode;

    @Column(name = "paid_at")
    private Instant paidAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected PaymentJpaEntity() {
    }

    public PaymentJpaEntity(UUID id, UUID reservationId, String paymentReference, String paymentMethod,
                            BigDecimal amount, String status, String gatewayTransactionId,
                            String gatewayResponseCode, Instant paidAt, Instant createdAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.paymentReference = paymentReference;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.status = status;
        this.gatewayTransactionId = gatewayTransactionId;
        this.gatewayResponseCode = gatewayResponseCode;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public UUID getReservationId() { return reservationId; }
    public String getPaymentReference() { return paymentReference; }
    public String getPaymentMethod() { return paymentMethod; }
    public BigDecimal getAmount() { return amount; }
    public String getStatus() { return status; }
    public Instant getPaidAt() { return paidAt; }
    public Instant getCreatedAt() { return createdAt; }
}
