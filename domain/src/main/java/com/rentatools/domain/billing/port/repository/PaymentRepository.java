package com.rentatools.domain.billing.port.repository;

import com.rentatools.domain.billing.model.aggregate.Payment;
import com.rentatools.domain.billing.model.valueobject.PaymentId;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Payment save(Payment payment);
    Optional<Payment> findById(PaymentId id);
    List<Payment> findByReservationId(ReservationId reservationId);
}
