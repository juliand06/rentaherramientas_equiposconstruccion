package com.rentatools.application.billing.exception;

import com.rentatools.application.common.exception.ApplicationException;

import java.util.UUID;

public class PaymentNotFoundException extends ApplicationException {

    public PaymentNotFoundException(UUID id) {
        super("No existe un pago con id " + id);
    }
}
