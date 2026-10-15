package com.rentatools.domain.rental.port.repository;

import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para persistencia de reservas y contratos de alquiler.
 */
public interface ReservationRepository {

    Reservation save(Reservation reservation);

    Optional<Reservation> findById(ReservationId id);

    List<Reservation> findByClientId(UserId clientId);

    List<Reservation> findAll();
}
