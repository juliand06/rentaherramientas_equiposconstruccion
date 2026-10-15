package com.rentatools.infrastructure.rental.adapters.out.persistence.mappers;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

public final class ReservationPersistenceMapper {

    private ReservationPersistenceMapper() {}

    public static Reservation toDomain(ReservationJpaEntity entity) {
        DateRange range = DateRange.of(
                entity.getStartDate() != null ? entity.getStartDate() : entity.getCreatedAt().atZone(java.time.ZoneOffset.UTC).toLocalDate(),
                entity.getEndDate() != null ? entity.getEndDate() : entity.getCreatedAt().atZone(java.time.ZoneOffset.UTC).toLocalDate().plusDays(1)
        );
        ToolId toolId = entity.getToolId() != null ? ToolId.of(entity.getToolId()) : ToolId.generate();
        Money dailyRate = entity.getDailyRate() != null ? Money.of(entity.getDailyRate()) : Money.of(entity.getTotalRentalAmount());

        return Reservation.restore(
                ReservationId.of(entity.getId()),
                UserId.of(entity.getClientId()),
                toolId,
                range,
                dailyRate,
                Money.of(entity.getTotalDepositAmount()),
                Reservation.ReservationStatus.valueOf(entity.getStatus()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static ReservationJpaEntity toEntity(Reservation domain) {
        String resNum = "RES-" + domain.getId().value().toString().substring(0, 8).toUpperCase();
        return new ReservationJpaEntity(
                domain.getId().value(),
                domain.getClientId().value(),
                resNum,
                domain.getStatus().name(),
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
