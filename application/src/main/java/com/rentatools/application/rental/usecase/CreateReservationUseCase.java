package com.rentatools.application.rental.usecase;

import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.application.rental.command.CreateReservationCommand;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.port.repository.ReservationRepository;

public class CreateReservationUseCase {

    private final ReservationRepository reservationRepository;
    private final TimeProvider timeProvider;

    public CreateReservationUseCase(ReservationRepository reservationRepository, TimeProvider timeProvider) {
        this.reservationRepository = reservationRepository;
        this.timeProvider = timeProvider;
    }

    public ReservationDto execute(CreateReservationCommand command) {
        DateRange rentalPeriod = DateRange.of(command.startDate(), command.endDate());
        Reservation reservation = Reservation.create(
                UserId.of(command.clientId()),
                ToolId.of(command.toolId()),
                rentalPeriod,
                Money.of(command.dailyRate()),
                Money.of(command.depositAmount()),
                timeProvider.now()
        );
        return ReservationDto.from(reservationRepository.save(reservation));
    }
}
