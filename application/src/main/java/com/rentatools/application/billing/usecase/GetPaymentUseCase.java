package com.rentatools.application.billing.usecase;

import com.rentatools.application.billing.dto.PaymentDto;
import com.rentatools.application.billing.exception.PaymentNotFoundException;
import com.rentatools.domain.billing.model.valueobject.PaymentId;
import com.rentatools.domain.billing.port.repository.PaymentRepository;

import java.util.UUID;

public class GetPaymentUseCase {

    private final PaymentRepository paymentRepository;

    public GetPaymentUseCase(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDto execute(UUID paymentId) {
        return paymentRepository.findById(PaymentId.of(paymentId))
                .map(PaymentDto::from)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
    }
}
