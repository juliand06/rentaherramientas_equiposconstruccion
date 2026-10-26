package com.rentatools.infrastructure.rental.adapters.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Entidad JPA para la tabla reporte_dano.
 * Se relaciona con Reserva (ManyToOne / OneToOne).
 */
@Entity
@Table(name = "reporte_dano")
public class ReporteDanoJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private ReservationJpaEntity reserva;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "costo_reparacion")
    private Double costoReparacion;

    @Column(name = "fecha_reporte", nullable = false)
    private Instant fechaReporte;

    protected ReporteDanoJpaEntity() {
    }

    public ReporteDanoJpaEntity(UUID id, ReservationJpaEntity reserva, String descripcion, Double costoReparacion, Instant fechaReporte) {
        this.id = id;
        this.reserva = reserva;
        this.descripcion = descripcion;
        this.costoReparacion = costoReparacion;
        this.fechaReporte = fechaReporte;
    }

    public UUID getId() {
        return id;
    }

    public ReservationJpaEntity getReserva() {
        return reserva;
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
