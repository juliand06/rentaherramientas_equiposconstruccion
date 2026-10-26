package com.rentatools.domain.rental.model.entity;

import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Entidad de dominio ReporteDano para registrar incidencias y costos de reparación
 * tras la devolución de una herramienta.
 */
public class ReporteDano {

    private final UUID id;
    private final ReservationId reservaId;
    private final String descripcion;
    private final Double costoReparacion;
    private final Instant fechaReporte;

    public ReporteDano(UUID id, ReservationId reservaId, String descripcion, Double costoReparacion, Instant fechaReporte) {
        this.id = id != null ? id : UUID.randomUUID();
        this.reservaId = Objects.requireNonNull(reservaId, "La reserva asociada no puede ser nula");
        this.descripcion = descripcion != null ? descripcion : "";
        this.costoReparacion = costoReparacion != null ? costoReparacion : 0.0;
        this.fechaReporte = fechaReporte != null ? fechaReporte : Instant.now();
    }

    public static ReporteDano create(ReservationId reservaId, String descripcion, Double costoReparacion, Instant now) {
        return new ReporteDano(UUID.randomUUID(), reservaId, descripcion, costoReparacion, now);
    }

    public UUID getId() {
        return id;
    }

    public ReservationId getReservaId() {
        return reservaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getCostoReparacion() {
        return costoReparacion;
    }

    public Instant getFechaReporte() {
        return fechaReporte;
    }
}
