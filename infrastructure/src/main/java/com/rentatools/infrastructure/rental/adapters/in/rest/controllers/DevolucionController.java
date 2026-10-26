package com.rentatools.infrastructure.rental.adapters.in.rest.controllers;

import com.rentatools.application.rental.dto.DevolucionDTO;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.service.DevolucionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Devoluciones", description = "Gestión del final del ciclo de vida de alquiler y reporte de daños (Examen 1.5H)")
@RestController
@RequestMapping("/api/reservas")
public class DevolucionController {

    private final DevolucionService devolucionService;

    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @Operation(
            summary = "Registrar devolución de herramienta e informe de daños",
            description = "Permite al proveedor marcar la devolución de una herramienta. Si el estado es 'OK', la reserva pasa a DEVUELTO_OK. Si es 'DAÑOS', pasa a DEVUELTO_CON_DAÑOS y se registra el reporte de daños.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolución registrada exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReservationDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de devolución inválidos"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado: Requiere rol Proveedor o Admin"),
            @ApiResponse(responseCode = "404", description = "Reserva no encontrada")
    })
    @PreAuthorize("hasAnyRole('PROVIDER', 'ADMIN')")
    @PostMapping("/{id}/registrar-devolucion")
    public ResponseEntity<ReservationDto> registrarDevolucion(
            @Parameter(description = "Identificador de la reserva (UUID o ID numérico)", required = true)
            @PathVariable("id") String id,
            @Valid @RequestBody DevolucionDTO dto) {
        ReservationDto response = devolucionService.registrarDevolucion(id, dto);
        return ResponseEntity.ok(response);
    }
}
