package com.rentatools.infrastructure.billing.adapters.out.persistence;

import com.rentatools.domain.billing.model.aggregate.Payment;
import com.rentatools.domain.billing.model.valueobject.PaymentId;
import com.rentatools.domain.billing.port.repository.PaymentRepository;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.infrastructure.billing.adapters.out.persistence.mappers.PaymentPersistenceMapper;
import com.rentatools.infrastructure.billing.adapters.out.persistence.repositories.PaymentJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public class PaymentRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository jpaRepository;

    public PaymentRepositoryAdapter(PaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        var entity = PaymentPersistenceMapper.toEntity(payment);
        return PaymentPersistenceMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        return jpaRepository.findById(id.value()).map(PaymentPersistenceMapper::toDomain);
    }

    @Override
    public List<Payment> findByReservationId(ReservationId reservationId) {
        return jpaRepository.findByReservationId(reservationId.value()).stream()
                .map(PaymentPersistenceMapper::toDomain)
                .toList();
    }
}
