package com.rentatools.application.rental;

import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReservationRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class InMemoryReservationRepository implements ReservationRepository {

    private final Map<ReservationId, Reservation> store = new LinkedHashMap<>();

    @Override
    public Reservation save(Reservation reservation) {
        store.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Reservation> findByClientId(UserId clientId) {
        return store.values().stream().filter(r -> r.getClientId().equals(clientId)).toList();
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }
}
