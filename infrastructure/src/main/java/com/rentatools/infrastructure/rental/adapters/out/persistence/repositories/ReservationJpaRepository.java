package com.rentatools.infrastructure.rental.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationJpaRepository extends JpaRepository<ReservationJpaEntity, UUID> {
    List<ReservationJpaEntity> findByClientId(UUID clientId);
}
