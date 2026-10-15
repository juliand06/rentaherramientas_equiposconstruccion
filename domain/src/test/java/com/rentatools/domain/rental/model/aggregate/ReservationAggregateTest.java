package com.rentatools.domain.rental.model.aggregate;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.DomainEvent;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.event.ReservationConfirmed;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationAggregateTest {

    private final UserId clientId = UserId.generate();
    private final ToolId toolId = ToolId.generate();
    private final Instant now = Instant.now();
    private final DateRange rentalPeriod = DateRange.of(LocalDate.of(2026, 10, 20), LocalDate.of(2026, 10, 24)); // 5 días

    @Test
    @DisplayName("Debe crear reserva y calcular total automáticamente")
    void shouldCreateReservationWithCalculatedTotal() {
        Money dailyRate = Money.of(new BigDecimal("50000.00"));
        Money deposit = Money.of(new BigDecimal("200000.00"));

        Reservation reservation = Reservation.create(clientId, toolId, rentalPeriod, dailyRate, deposit, now);

        assertEquals(Reservation.ReservationStatus.PENDING, reservation.getStatus());
        assertEquals(new BigDecimal("250000.00"), reservation.getTotalRentalAmount().amount());
    }

    @Test
    @DisplayName("Confirmar reserva emite evento ReservationConfirmed y cambia a CONFIRMED")
    void shouldConfirmReservationAndEmitEvent() {
        Reservation reservation = Reservation.create(
                clientId,
                toolId,
                rentalPeriod,
                Money.of(new BigDecimal("50000.00")),
                Money.of(new BigDecimal("200000.00")),
                now
        );

        reservation.confirm(now);
        assertEquals(Reservation.ReservationStatus.CONFIRMED, reservation.getStatus());

        List<DomainEvent> events = reservation.pullDomainEvents();
        assertEquals(1, events.size());
        assertTrue(events.getFirst() instanceof ReservationConfirmed);

        // pullDomainEvents limpia los eventos registrados
        assertTrue(reservation.pullDomainEvents().isEmpty());
    }
}
