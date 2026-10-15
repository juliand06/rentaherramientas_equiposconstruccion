package com.rentatools.domain.billing.model.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record PaymentId(UUID value) implements Serializable {

    public PaymentId {
        Objects.requireNonNull(value, "El ID de pago no puede ser nulo");
    }

    public static PaymentId generate() {
        return new PaymentId(UUID.randomUUID());
    }

    public static PaymentId of(UUID value) {
        return new PaymentId(value);
    }
}
