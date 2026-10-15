package com.rentatools.application.rental.usecase;

import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.exception.ReservationNotFoundException;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReservationRepository;

import java.util.UUID;

public class GetReservationUseCase {

    private final ReservationRepository reservationRepository;

    public GetReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto execute(UUID reservationId) {
        return reservationRepository.findById(ReservationId.of(reservationId))
                .map(ReservationDto::from)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
    }
}
