package com.rentatools.infrastructure.rental.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReporteDanoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReporteDanoJpaRepository extends JpaRepository<ReporteDanoJpaEntity, UUID> {

    @Query("SELECT r FROM ReporteDanoJpaEntity r WHERE r.reserva.id = :reservaId")
    List<ReporteDanoJpaEntity> findByReservaId(@Param("reservaId") UUID reservaId);
}
