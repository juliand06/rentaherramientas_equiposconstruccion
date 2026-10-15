package com.rentatools.domain.billing.model.aggregate;

import com.rentatools.domain.billing.model.valueobject.PaymentId;
import com.rentatools.domain.common.AggregateRoot;
import com.rentatools.domain.common.DomainException;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.time.Instant;
import java.util.Objects;

public class Payment extends AggregateRoot<PaymentId> {

    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }

    public enum PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        PSE,
        BANK_TRANSFER,
        SIMULATED_GATEWAY
    }

    private final ReservationId reservationId;
    private final String paymentReference;
    private final PaymentMethod paymentMethod;
    private final Money amount;
    private PaymentStatus status;
    private Instant paidAt;
    private final Instant createdAt;

    private Payment(PaymentId id, ReservationId reservationId, String paymentReference,
                    PaymentMethod paymentMethod, Money amount, PaymentStatus status,
                    Instant paidAt, Instant createdAt) {
        super(id);
        this.reservationId = Objects.requireNonNull(reservationId, "La reserva no puede ser nula");
        this.paymentReference = Objects.requireNonNull(paymentReference, "La referencia no puede ser nula");
        this.paymentMethod = Objects.requireNonNull(paymentMethod, "El método de pago no puede ser nulo");
        this.amount = Objects.requireNonNull(amount, "El monto no puede ser nulo");
        if (!amount.isPositive()) {
            throw new InvalidPaymentDataException("El monto a pagar debe ser positivo");
        }
        this.status = Objects.requireNonNull(status, "El estado no puede ser nulo");
        this.paidAt = paidAt;
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
    }

    public static Payment initiate(ReservationId reservationId, PaymentMethod method, Money amount, Instant now) {
        String ref = "PAY-" + System.currentTimeMillis() + "-" + reservationId.value().toString().substring(0, 4).toUpperCase();
        return new Payment(PaymentId.generate(), reservationId, ref, method, amount, PaymentStatus.PENDING, null, now);
    }

    public static Payment restore(PaymentId id, ReservationId reservationId, String paymentReference,
                                  PaymentMethod paymentMethod, Money amount, PaymentStatus status,
                                  Instant paidAt, Instant createdAt) {
        return new Payment(id, reservationId, paymentReference, paymentMethod, amount, status, paidAt, createdAt);
    }

    public void complete(Instant now) {
        if (this.status != PaymentStatus.PENDING) {
            throw new InvalidPaymentStateException("Solo se puede completar un pago pendiente");
        }
        this.status = PaymentStatus.COMPLETED;
        this.paidAt = Objects.requireNonNull(now);
    }

    public void fail() {
        if (this.status != PaymentStatus.PENDING) {
            throw new InvalidPaymentStateException("Solo se puede fallar un pago pendiente");
        }
        this.status = PaymentStatus.FAILED;
    }

    public ReservationId getReservationId() { return reservationId; }
    public String getPaymentReference() { return paymentReference; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public Money getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public Instant getPaidAt() { return paidAt; }
    public Instant getCreatedAt() { return createdAt; }

    public static class InvalidPaymentStateException extends DomainException {
        public InvalidPaymentStateException(String message) { super(message); }
    }

    public static class InvalidPaymentDataException extends DomainException {
        public InvalidPaymentDataException(String message) { super(message); }
    }
}
