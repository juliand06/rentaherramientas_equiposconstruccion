package com.rentatools.application.rental.usecase;

import com.rentatools.application.common.event.DomainEventPublisher;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.exception.ReservationNotFoundException;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReservationRepository;

import java.time.Instant;
import java.util.UUID;

public class ConfirmReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final DomainEventPublisher eventPublisher;
    private final TimeProvider timeProvider;

    public ConfirmReservationUseCase(ReservationRepository reservationRepository,
                                     DomainEventPublisher eventPublisher,
                                     TimeProvider timeProvider) {
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
        this.timeProvider = timeProvider;
    }

    public ReservationDto execute(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(ReservationId.of(reservationId))
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        Instant now = timeProvider.now();
        reservation.confirm(now);
        reservation.pullDomainEvents().forEach(eventPublisher::publish);

        return ReservationDto.from(reservationRepository.save(reservation));
    }
}
