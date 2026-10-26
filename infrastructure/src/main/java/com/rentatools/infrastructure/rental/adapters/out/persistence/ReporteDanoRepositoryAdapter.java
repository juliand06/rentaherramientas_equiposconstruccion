package com.rentatools.infrastructure.rental.adapters.out.persistence;

import com.rentatools.domain.rental.model.entity.ReporteDano;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReporteDanoRepository;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReporteDanoJpaEntity;
import com.rentatools.infrastructure.rental.adapters.out.persistence.entity.ReservationJpaEntity;
import com.rentatools.infrastructure.rental.adapters.out.persistence.repositories.ReporteDanoJpaRepository;
import com.rentatools.infrastructure.rental.adapters.out.persistence.repositories.ReservationJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional(readOnly = true)
public class ReporteDanoRepositoryAdapter implements ReporteDanoRepository {

    private final ReporteDanoJpaRepository jpaRepository;
    private final ReservationJpaRepository reservationJpaRepository;

    public ReporteDanoRepositoryAdapter(ReporteDanoJpaRepository jpaRepository,
                                       ReservationJpaRepository reservationJpaRepository) {
        this.jpaRepository = jpaRepository;
        this.reservationJpaRepository = reservationJpaRepository;
    }

    @Override
    @Transactional
    public ReporteDano save(ReporteDano reporteDano) {
        ReservationJpaEntity reservaRef = reservationJpaRepository.getReferenceById(reporteDano.getReservaId().value());
        ReporteDanoJpaEntity entity = new ReporteDanoJpaEntity(
                reporteDano.getId(),
                reservaRef,
                reporteDano.getDescripcion(),
                reporteDano.getCostoReparacion(),
                reporteDano.getFechaReporte()
        );
        ReporteDanoJpaEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<ReporteDano> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<ReporteDano> findByReservaId(ReservationId reservaId) {
        return jpaRepository.findByReservaId(reservaId.value()).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<ReporteDano> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private ReporteDano toDomain(ReporteDanoJpaEntity entity) {
        return new ReporteDano(
                entity.getId(),
                ReservationId.of(entity.getReserva().getId()),
                entity.getDescripcion(),
                entity.getCostoReparacion(),
                entity.getFechaReporte()
        );
    }
}
