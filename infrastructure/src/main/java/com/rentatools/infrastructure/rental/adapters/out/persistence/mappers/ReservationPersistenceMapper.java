package com.rentatools.infrastructure.rental.adapters.out.persistence.mappers;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.EstadoDevolucion;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;

import java.time.temporal.ChronoUnit;

public final class ReservationPersistenceMapper {

    private ReservationPersistenceMapper() {}

    public static Reservation toDomain(ReservationJpaEntity entity) {
        DateRange range = DateRange.of(
                entity.getStartDate() != null ? entity.getStartDate() : entity.getCreatedAt().atZone(java.time.ZoneOffset.UTC).toLocalDate(),
                entity.getEndDate() != null ? entity.getEndDate() : entity.getCreatedAt().atZone(java.time.ZoneOffset.UTC).toLocalDate().plusDays(1)
        );
        ToolId toolId = entity.getToolId() != null ? ToolId.of(entity.getToolId()) : ToolId.generate();
        Money dailyRate = entity.getDailyRate() != null ? Money.of(entity.getDailyRate()) : Money.of(entity.getTotalRentalAmount());

        EstadoDevolucion estadoDevolucion = EstadoDevolucion.fromString(entity.getEstadoDevolucion());

        Reservation.ReservationStatus status;
        try {
            status = Reservation.ReservationStatus.valueOf(entity.getStatus());
        } catch (IllegalArgumentException e) {
            status = Reservation.ReservationStatus.COMPLETED;
        }

        return Reservation.restore(
                ReservationId.of(entity.getId()),
                UserId.of(entity.getClientId()),
                toolId,
                range,
                dailyRate,
                Money.of(entity.getTotalDepositAmount()),
                status,
                estadoDevolucion,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ReservationJpaEntity toEntity(Reservation domain) {
        return toEntity(domain, null);
    }

    public static ReservationJpaEntity toEntity(Reservation domain, String existingResNum) {
        String resNum = existingResNum != null ? existingResNum : "RES-" + domain.getId().value().toString().replace("-", "").substring(0, 12).toUpperCase();
        return new ReservationJpaEntity(
                domain.getId().value(),
                domain.getClientId().value(),
                resNum,
                domain.getStatus().name(),
                domain.getEstadoDevolucion().name(),
                domain.getTotalRentalAmount().amount(),
                domain.getDepositAmount().amount(),
                domain.getCreatedAt().plus(48, ChronoUnit.HOURS),
                null,
                domain.getRentalPeriod().startDate(),
                domain.getRentalPeriod().endDate(),
                domain.getDailyRate().amount(),
                domain.getToolId().value(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}
