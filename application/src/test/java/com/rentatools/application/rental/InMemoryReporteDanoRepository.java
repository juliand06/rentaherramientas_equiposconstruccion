package com.rentatools.application.rental;

import com.rentatools.domain.rental.model.entity.ReporteDano;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import com.rentatools.domain.rental.port.repository.ReporteDanoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryReporteDanoRepository implements ReporteDanoRepository {

    private final List<ReporteDano> reportes = new ArrayList<>();

    @Override
    public ReporteDano save(ReporteDano reporteDano) {
        reportes.removeIf(r -> r.getId().equals(reporteDano.getId()));
        reportes.add(reporteDano);
        return reporteDano;
    }

    @Override
    public Optional<ReporteDano> findById(UUID id) {
        return reportes.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    @Override
    public List<ReporteDano> findByReservaId(ReservationId reservaId) {
        return reportes.stream().filter(r -> r.getReservaId().equals(reservaId)).toList();
    }

    @Override
    public List<ReporteDano> findAll() {
        return new ArrayList<>(reportes);
    }
}
