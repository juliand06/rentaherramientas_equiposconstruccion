package com.rentatools.infrastructure.billing.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.billing.adapters.out.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, UUID> {
    List<PaymentJpaEntity> findByReservationId(UUID reservationId);
}
