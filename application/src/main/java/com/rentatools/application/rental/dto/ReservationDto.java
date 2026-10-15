package com.rentatools.application.rental.dto;

import com.rentatools.domain.rental.model.aggregate.Reservation;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ReservationDto(
        UUID id,
        UUID clientId,
        UUID toolId,
        LocalDate startDate,
        LocalDate endDate,
        long totalDays,
        BigDecimal dailyRate,
        BigDecimal totalRentalAmount,
        BigDecimal depositAmount,
        String status,
        Instant createdAt,
        Instant updatedAt
) {

    public static ReservationDto from(Reservation res) {
        return new ReservationDto(
                res.getId().value(),
                res.getClientId().value(),
                res.getToolId().value(),
                res.getRentalPeriod().startDate(),
                res.getRentalPeriod().endDate(),
                res.getRentalPeriod().totalDays(),
                res.getDailyRate().amount(),
                res.getTotalRentalAmount().amount(),
                res.getDepositAmount().amount(),
                res.getStatus().name(),
                res.getCreatedAt(),
                res.getUpdatedAt()
        );
    }
}
