package com.rentatools.infrastructure.billing.adapters.out.persistence.mappers;

import com.rentatools.domain.billing.model.aggregate.Payment;
import com.rentatools.domain.billing.model.valueobject.PaymentId;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.infrastructure.billing.adapters.out.persistence.entity.PaymentJpaEntity;

public final class PaymentPersistenceMapper {

    private PaymentPersistenceMapper() {}

    public static Payment toDomain(PaymentJpaEntity entity) {
        return Payment.restore(
                PaymentId.of(entity.getId()),
                ReservationId.of(entity.getReservationId()),
                entity.getPaymentReference(),
                Payment.PaymentMethod.valueOf(entity.getPaymentMethod()),
                Money.of(entity.getAmount()),
                Payment.PaymentStatus.valueOf(entity.getStatus()),
                entity.getPaidAt(),
                entity.getCreatedAt()
        );
    }

    public static PaymentJpaEntity toEntity(Payment domain) {
        return new PaymentJpaEntity(
                domain.getId().value(),
                domain.getReservationId().value(),
                domain.getPaymentReference(),
                domain.getPaymentMethod().name(),
                domain.getAmount().amount(),
                domain.getStatus().name(),
                "GTW-" + domain.getId().value().toString().substring(0, 8),
                "00_APPROVED",
                domain.getPaidAt(),
                domain.getCreatedAt()
        );
    }
}
