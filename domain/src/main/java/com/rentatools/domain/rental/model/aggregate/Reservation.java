package com.rentatools.domain.rental.model.aggregate;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.AggregateRoot;
import com.rentatools.domain.common.DomainException;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.event.ReservationConfirmed;
import com.rentatools.domain.rental.model.valueobject.ReservationId;

import java.time.Instant;
import java.util.Objects;

/**
 * Agregado Reservation (Gestión de alquiler y contratos de herramientas).
 * Maneja el ciclo de vida: PENDING -> CONFIRMED -> ACTIVE -> COMPLETED (o CANCELLED).
 */
public class Reservation extends AggregateRoot<ReservationId> {

    public enum ReservationStatus {
        PENDING,
        CONFIRMED,
        ACTIVE,
        COMPLETED,
        CANCELLED
    }

    private final UserId clientId;
    private final ToolId toolId;
    private final DateRange rentalPeriod;
    private final Money dailyRate;
    private final Money totalRentalAmount;
    private final Money depositAmount;
    private ReservationStatus status;
    private EstadoDevolucion estadoDevolucion;
    private final Instant createdAt;
    private Instant updatedAt;

    private Reservation(
            ReservationId id,
            UserId clientId,
            ToolId toolId,
            DateRange rentalPeriod,
            Money dailyRate,
            Money depositAmount,
            ReservationStatus status,
            EstadoDevolucion estadoDevolucion,
            Instant createdAt,
            Instant updatedAt
    ) {
        super(id);
        this.clientId = Objects.requireNonNull(clientId, "El cliente no puede ser nulo");
        this.toolId = Objects.requireNonNull(toolId, "La herramienta no puede ser nula");
        this.rentalPeriod = Objects.requireNonNull(rentalPeriod, "El periodo de alquiler no puede ser nulo");
        this.dailyRate = Objects.requireNonNull(dailyRate, "La tarifa diaria no puede ser nula");
        this.depositAmount = Objects.requireNonNull(depositAmount, "El depósito de garantía no puede ser nulo");
        this.totalRentalAmount = this.dailyRate.multiply(this.rentalPeriod.totalDays());
        this.status = Objects.requireNonNull(status, "El estado no puede ser nulo");
        this.estadoDevolucion = estadoDevolucion != null ? estadoDevolucion : EstadoDevolucion.PENDIENTE_DEVOLUCION;
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
    }

    public static Reservation create(
            UserId clientId,
            ToolId toolId,
            DateRange rentalPeriod,
            Money dailyRate,
            Money depositAmount,
            Instant now
    ) {
        ReservationId id = ReservationId.generate();
        return new Reservation(
                id,
                clientId,
                toolId,
                rentalPeriod,
                dailyRate,
                depositAmount,
                ReservationStatus.PENDING,
                EstadoDevolucion.PENDIENTE_DEVOLUCION,
                now,
                now
        );
    }

    public static Reservation restore(
            ReservationId id,
            UserId clientId,
            ToolId toolId,
            DateRange rentalPeriod,
            Money dailyRate,
            Money depositAmount,
            ReservationStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        return restore(
                id,
                clientId,
                toolId,
                rentalPeriod,
                dailyRate,
                depositAmount,
                status,
                EstadoDevolucion.PENDIENTE_DEVOLUCION,
                createdAt,
                updatedAt
        );
    }

    public static Reservation restore(
            ReservationId id,
            UserId clientId,
            ToolId toolId,
            DateRange rentalPeriod,
            Money dailyRate,
            Money depositAmount,
            ReservationStatus status,
            EstadoDevolucion estadoDevolucion,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new Reservation(
                id,
                clientId,
                toolId,
                rentalPeriod,
                dailyRate,
                depositAmount,
                status,
                estadoDevolucion,
                createdAt,
                updatedAt
        );
    }

    public void registrarDevolucion(EstadoDevolucion estado, Instant now) {
        this.estadoDevolucion = Objects.requireNonNull(estado, "El estado de devolución no puede ser nulo");
        this.status = ReservationStatus.COMPLETED;
        this.updatedAt = Objects.requireNonNull(now, "La fecha de actualización no puede ser nula");
    }

    public void confirm(Instant now) {
        if (this.status != ReservationStatus.PENDING) {
            throw new InvalidReservationStateException("Solo se puede confirmar una reserva en estado PENDIENTE. Estado actual: " + this.status);
        }
        this.status = ReservationStatus.CONFIRMED;
        this.updatedAt = Objects.requireNonNull(now);
        registerEvent(new ReservationConfirmed(this.id, this.clientId, this.toolId, this.rentalPeriod, now));
    }

    public void startRental(Instant now) {
        if (this.status != ReservationStatus.CONFIRMED) {
            throw new InvalidReservationStateException("Solo se puede iniciar la entrega de una reserva CONFIRMADA");
        }
        this.status = ReservationStatus.ACTIVE;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void complete(Instant now) {
        if (this.status != ReservationStatus.ACTIVE) {
            throw new InvalidReservationStateException("Solo se puede completar una reserva ACTIVA");
        }
        this.status = ReservationStatus.COMPLETED;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void cancel(Instant now) {
        if (this.status == ReservationStatus.ACTIVE || this.status == ReservationStatus.COMPLETED) {
            throw new InvalidReservationStateException("No se puede cancelar una reserva activa o completada");
        }
        this.status = ReservationStatus.CANCELLED;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public UserId getClientId() {
        return clientId;
    }

    public ToolId getToolId() {
        return toolId;
    }

    public DateRange getRentalPeriod() {
        return rentalPeriod;
    }

    public Money getDailyRate() {
        return dailyRate;
    }

    public Money getTotalRentalAmount() {
        return totalRentalAmount;
    }

    public Money getDepositAmount() {
        return depositAmount;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public EstadoDevolucion getEstadoDevolucion() {
        return estadoDevolucion != null ? estadoDevolucion : EstadoDevolucion.PENDIENTE_DEVOLUCION;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public static class InvalidReservationStateException extends DomainException {
        public InvalidReservationStateException(String message) {
            super(message);
        }
    }
}
