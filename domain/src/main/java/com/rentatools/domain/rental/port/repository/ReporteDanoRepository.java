package com.rentatools.domain.rental.port.repository;

import com.rentatools.domain.rental.model.entity.ReporteDano;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para persistencia de reportes de daños.
 */
public interface ReporteDanoRepository {
    ReporteDano save(ReporteDano reporteDano);
    Optional<ReporteDano> findById(UUID id);
    List<ReporteDano> findByReservaId(ReservationId reservaId);
    List<ReporteDano> findAll();
}
