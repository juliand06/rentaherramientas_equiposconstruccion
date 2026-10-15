package com.rentatools.application.rental.command;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateReservationCommand(
        UUID clientId,
        UUID toolId,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal dailyRate,
        BigDecimal depositAmount
) {
}
