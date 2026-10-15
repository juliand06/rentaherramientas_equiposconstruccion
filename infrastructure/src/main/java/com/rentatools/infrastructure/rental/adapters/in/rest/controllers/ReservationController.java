package com.rentatools.infrastructure.rental.adapters.in.rest.controllers;

import com.rentatools.application.rental.command.CreateReservationCommand;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.usecase.ConfirmReservationUseCase;
import com.rentatools.application.rental.usecase.CreateReservationUseCase;
import com.rentatools.application.rental.usecase.GetReservationUseCase;
import com.rentatools.infrastructure.rental.adapters.in.rest.dtos.CreateReservationRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservation;
    private final ConfirmReservationUseCase confirmReservation;
    private final GetReservationUseCase getReservation;

    public ReservationController(CreateReservationUseCase createReservation,
                                 ConfirmReservationUseCase confirmReservation,
                                 GetReservationUseCase getReservation) {
        this.createReservation = createReservation;
        this.confirmReservation = confirmReservation;
        this.getReservation = getReservation;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto create(@Valid @RequestBody CreateReservationRequest request) {
        return createReservation.execute(new CreateReservationCommand(
                request.clientId(),
                request.toolId(),
                request.startDate(),
                request.endDate(),
                request.dailyRate(),
                request.depositAmount()
        ));
    }

    @PutMapping("/{id}/confirm")
    public ReservationDto confirm(@PathVariable UUID id) {
        return confirmReservation.execute(id);
    }

    @GetMapping("/{id}")
    public ReservationDto get(@PathVariable UUID id) {
        return getReservation.execute(id);
    }
}
