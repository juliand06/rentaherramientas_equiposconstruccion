package com.rentatools.application.rental.exception;

import com.rentatools.application.common.exception.ApplicationException;

import java.util.UUID;

public class ReservationNotFoundException extends ApplicationException {

    public ReservationNotFoundException(UUID id) {
        super("No existe una reserva con id " + id);
    }
}
