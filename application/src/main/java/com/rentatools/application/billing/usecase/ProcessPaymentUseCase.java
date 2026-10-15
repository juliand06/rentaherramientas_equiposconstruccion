package com.rentatools.application.billing.usecase;

import com.rentatools.application.billing.command.ProcessPaymentCommand;
import com.rentatools.application.billing.dto.PaymentDto;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.domain.billing.model.aggregate.Payment;
import com.rentatools.domain.billing.port.repository.PaymentRepository;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.time.Instant;

public class ProcessPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final TimeProvider timeProvider;

    public ProcessPaymentUseCase(PaymentRepository paymentRepository, TimeProvider timeProvider) {
        this.paymentRepository = paymentRepository;
        this.timeProvider = timeProvider;
    }

    public PaymentDto execute(ProcessPaymentCommand command) {
        Instant now = timeProvider.now();
        Payment payment = Payment.initiate(
                ReservationId.of(command.reservationId()),
                command.paymentMethod(),
                Money.of(command.amount()),
                now
        );
        // Simulación de pasarela de pago exitosa
        payment.complete(now);
        return PaymentDto.from(paymentRepository.save(payment));
    }
}
