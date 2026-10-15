package com.rentatools.domain.rental.model.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Identificador fuertemente tipado para el agregado Reservation.
 */
public record ReservationId(UUID value) implements Serializable {

    public ReservationId {
        Objects.requireNonNull(value, "El identificador de la reserva no puede ser nulo");
    }

    public static ReservationId generate() {
        return new ReservationId(UUID.randomUUID());
    }

    public static ReservationId of(UUID value) {
        return new ReservationId(value);
    }

    public static ReservationId of(String value) {
        try {
            return new ReservationId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw new InvalidReservationIdException("UUID de reserva inválido: " + value);
        }
    }

    public static class InvalidReservationIdException extends DomainException {
        public InvalidReservationIdException(String message) {
            super(message);
        }
    }
}
