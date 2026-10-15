package com.rentatools.infrastructure.rental.adapters.in.rest.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationRequest(
        @NotNull UUID clientId,
        @NotNull UUID toolId,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull @DecimalMin("0.01") BigDecimal dailyRate,
        @NotNull @DecimalMin("0.00") BigDecimal depositAmount
) {
}
