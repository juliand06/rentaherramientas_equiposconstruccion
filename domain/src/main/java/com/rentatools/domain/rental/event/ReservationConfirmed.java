package com.rentatools.domain.rental.event;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.DomainEvent;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.time.Instant;
import java.util.Objects;

/**
 * Evento emitido cuando una reserva es confirmada formalmente.
 */
public record ReservationConfirmed(
        ReservationId reservationId,
        UserId clientId,
        ToolId toolId,
        DateRange rentalPeriod,
        Instant occurredOn
) implements DomainEvent {

    public ReservationConfirmed {
        Objects.requireNonNull(reservationId, "El identificador de reserva no puede ser nulo");
        Objects.requireNonNull(clientId, "El cliente no puede ser nulo");
        Objects.requireNonNull(toolId, "La herramienta no puede ser nula");
        Objects.requireNonNull(rentalPeriod, "El periodo no puede ser nulo");
        Objects.requireNonNull(occurredOn, "occurredOn no puede ser nulo");
    }
}
