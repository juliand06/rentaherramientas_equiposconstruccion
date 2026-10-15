package com.rentatools.infrastructure.billing.adapters.in.rest.controllers;

import com.rentatools.application.billing.command.ProcessPaymentCommand;
import com.rentatools.application.billing.dto.PaymentDto;
import com.rentatools.application.billing.usecase.GetPaymentUseCase;
import com.rentatools.application.billing.usecase.ProcessPaymentUseCase;
import com.rentatools.infrastructure.billing.adapters.in.rest.dtos.ProcessPaymentRequest;
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

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final ProcessPaymentUseCase processPayment;
    private final GetPaymentUseCase getPayment;

    public PaymentController(ProcessPaymentUseCase processPayment, GetPaymentUseCase getPayment) {
        this.processPayment = processPayment;
        this.getPayment = getPayment;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto process(@Valid @RequestBody ProcessPaymentRequest request) {
        return processPayment.execute(new ProcessPaymentCommand(
                request.reservationId(),
                request.paymentMethod(),
                request.amount()
        ));
    }

    @GetMapping("/{id}")
    public PaymentDto get(@PathVariable UUID id) {
        return getPayment.execute(id);
    }
}
