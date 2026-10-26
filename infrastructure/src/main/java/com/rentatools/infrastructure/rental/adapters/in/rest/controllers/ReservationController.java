package com.rentatools.infrastructure.rental.adapters.in.rest.controllers;

import com.rentatools.application.rental.command.CreateReservationCommand;
import com.rentatools.application.rental.dto.DevolucionDTO;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.service.DevolucionService;
import com.rentatools.application.rental.usecase.ConfirmReservationUseCase;
import com.rentatools.application.rental.usecase.CreateReservationUseCase;
import com.rentatools.application.rental.usecase.GetReservationUseCase;
import com.rentatools.infrastructure.rental.adapters.in.rest.dtos.CreateReservationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Reservaciones", description = "Gestión del ciclo de vida de alquiler y contratos de herramientas")
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final CreateReservationUseCase createReservation;
    private final ConfirmReservationUseCase confirmReservation;
    private final GetReservationUseCase getReservation;
    private final DevolucionService devolucionService;

    public ReservationController(CreateReservationUseCase createReservation,
                                 ConfirmReservationUseCase confirmReservation,
                                 GetReservationUseCase getReservation) {
        this(createReservation, confirmReservation, getReservation, null);
    }

    @org.springframework.beans.factory.annotation.Autowired
    public ReservationController(CreateReservationUseCase createReservation,
                                 ConfirmReservationUseCase confirmReservation,
                                 GetReservationUseCase getReservation,
                                 DevolucionService devolucionService) {
        this.createReservation = createReservation;
        this.confirmReservation = confirmReservation;
        this.getReservation = getReservation;
        this.devolucionService = devolucionService;
    }

    @Operation(summary = "Crear nueva reservación", description = "Crea una reservación en estado PENDING con cálculo automático de costos", security = @SecurityRequirement(name = "bearerAuth"))
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

    @Operation(summary = "Confirmar reservación", description = "Pasa la reservación a estado CONFIRMED", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/{id}/confirm")
    public ReservationDto confirm(@Parameter(description = "ID de la reservación") @PathVariable UUID id) {
        return confirmReservation.execute(id);
    }

    @Operation(summary = "Consultar reservación por ID", description = "Obtiene los detalles de una reservación", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ReservationDto get(@Parameter(description = "ID de la reservación") @PathVariable UUID id) {
        return getReservation.execute(id);
    }

    @Operation(summary = "Registrar devolución de herramienta", description = "Marca la devolución como OK o DEVUELTO_CON_DAÑOS", security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    @PostMapping("/{id}/registrar-devolucion")
    public ReservationDto registrarDevolucion(
            @Parameter(description = "ID de la reserva") @PathVariable String id,
            @Valid @RequestBody DevolucionDTO dto) {
        return devolucionService.registrarDevolucion(id, dto);
    }
}
