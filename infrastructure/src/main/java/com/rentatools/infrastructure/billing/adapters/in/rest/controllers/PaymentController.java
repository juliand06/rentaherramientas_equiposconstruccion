package com.rentatools.infrastructure.billing.adapters.in.rest.controllers;

import com.rentatools.application.billing.command.ProcessPaymentCommand;
import com.rentatools.application.billing.dto.PaymentDto;
import com.rentatools.application.billing.usecase.GetPaymentUseCase;
import com.rentatools.application.billing.usecase.ProcessPaymentUseCase;
import com.rentatools.infrastructure.billing.adapters.in.rest.dtos.ProcessPaymentRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Pagos", description = "Procesamiento de pagos y pasarela de cobros de alquiler")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processPayment;
    private final GetPaymentUseCase getPayment;

    public PaymentController(ProcessPaymentUseCase processPayment, GetPaymentUseCase getPayment) {
        this.processPayment = processPayment;
        this.getPayment = getPayment;
    }

    @Operation(summary = "Procesar pago de reserva", description = "Registra un pago con tarjeta, PSE o transferencia bancaria", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto process(@Valid @RequestBody ProcessPaymentRequest request) {
        return processPayment.execute(new ProcessPaymentCommand(
                request.reservationId(),
                request.paymentMethod(),
                request.amount()
        ));
    }

    @Operation(summary = "Consultar pago por ID", description = "Obtiene los detalles del pago registrado", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public PaymentDto get(@Parameter(description = "ID del pago") @PathVariable UUID id) {
        return getPayment.execute(id);
    }
}
