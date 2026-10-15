package com.rentatools.infrastructure.billing.adapters.in.rest.dtos;

import com.rentatools.domain.billing.model.aggregate.Payment.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessPaymentRequest(
        @NotNull UUID reservationId,
        @NotNull PaymentMethod paymentMethod,
        @NotNull @DecimalMin("0.01") BigDecimal amount
) {
}
