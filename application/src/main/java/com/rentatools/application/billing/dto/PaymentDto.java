package com.rentatools.application.billing.dto;

import com.rentatools.domain.billing.model.aggregate.Payment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PaymentDto(
        UUID id,
        UUID reservationId,
        String paymentReference,
        String paymentMethod,
        BigDecimal amount,
        String status,
        Instant paidAt,
        Instant createdAt
) {

    public static PaymentDto from(Payment payment) {
        return new PaymentDto(
                payment.getId().value(),
                payment.getReservationId().value(),
                payment.getPaymentReference(),
                payment.getPaymentMethod().name(),
                payment.getAmount().amount(),
                payment.getStatus().name(),
                payment.getPaidAt(),
                payment.getCreatedAt()
        );
    }
}
