package com.rentatools.infrastructure.rental.adapters.out.persistence;

import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReservationRepository;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;
import com.rentatools.infrastructure.rental.adapters.out.persistence.mappers.ReservationPersistenceMapper;
import com.rentatools.infrastructure.rental.adapters.out.persistence.repositories.ReservationJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public class ReservationRepositoryAdapter implements ReservationRepository {

    private final ReservationJpaRepository jpaRepository;

    public ReservationRepositoryAdapter(ReservationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional
    public Reservation save(Reservation reservation) {
        String existingResNum = jpaRepository.findById(reservation.getId().value())
                .map(ReservationJpaEntity::getReservationNumber)
                .orElse(null);
        var entity = ReservationPersistenceMapper.toEntity(reservation, existingResNum);
        return ReservationPersistenceMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Reservation> findById(ReservationId id) {
        return jpaRepository.findById(id.value()).map(ReservationPersistenceMapper::toDomain);
    }

    @Override
    public List<Reservation> findByClientId(UserId clientId) {
        return jpaRepository.findByClientId(clientId.value()).stream()
                .map(ReservationPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public List<Reservation> findAll() {
        return jpaRepository.findAll().stream()
                .map(ReservationPersistenceMapper::toDomain)
                .toList();
    }
}
