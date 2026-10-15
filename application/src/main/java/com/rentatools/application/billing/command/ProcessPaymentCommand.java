package com.rentatools.application.billing.command;

import com.rentatools.domain.billing.model.aggregate.Payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessPaymentCommand(
        UUID reservationId,
        PaymentMethod paymentMethod,
        BigDecimal amount
) {
}
