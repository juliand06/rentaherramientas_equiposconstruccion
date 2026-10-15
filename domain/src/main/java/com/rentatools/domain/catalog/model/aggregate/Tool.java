package com.rentatools.domain.catalog.model.aggregate;

import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.AggregateRoot;
import com.rentatools.domain.common.DomainException;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Agregado Tool (Catálogo de herramientas y equipos de construcción).
 * Encapsula las reglas de negocio sobre disponibilidad, precios de renta y depósito de garantía.
 */
public class Tool extends AggregateRoot<ToolId> {

    public enum ToolStatus {
        AVAILABLE,
        RENTED,
        MAINTENANCE,
        INACTIVE
    }

    private final UserId providerId;
    private final UUID categoryId;
    private String name;
    private String description;
    private String brand;
    private String model;
    private Money dailyPrice;
    private Money securityDeposit;
    private ToolStatus status;
    private final Instant createdAt;
    private Instant updatedAt;

    // Constructor privado para cumplir DDD (fábricas create / restore)
    private Tool(
            ToolId id,
            UserId providerId,
            UUID categoryId,
            String name,
            String description,
            String brand,
            String model,
            Money dailyPrice,
            Money securityDeposit,
            ToolStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        super(id);
        this.providerId = Objects.requireNonNull(providerId, "El proveedor no puede ser nulo");
        this.categoryId = Objects.requireNonNull(categoryId, "La categoría no puede ser nula");
        this.name = validateNonBlank(name, "El nombre de la herramienta");
        this.description = description == null ? "" : description.trim();
        this.brand = validateNonBlank(brand, "La marca");
        this.model = validateNonBlank(model, "El modelo");
        this.dailyPrice = validatePositiveMoney(dailyPrice, "El precio diario");
        this.securityDeposit = validateZeroOrPositiveMoney(securityDeposit, "El depósito de garantía");
        this.status = Objects.requireNonNull(status, "El estado de la herramienta no puede ser nulo");
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
    }

    /**
     * Factoría para crear una nueva herramienta en el catálogo.
     */
    public static Tool create(
            UserId providerId,
            UUID categoryId,
            String name,
            String description,
            String brand,
            String model,
            Money dailyPrice,
            Money securityDeposit,
            Instant now
    ) {
        ToolId toolId = ToolId.generate();
        return new Tool(
                toolId,
                providerId,
                categoryId,
                name,
                description,
                brand,
                model,
                dailyPrice,
                securityDeposit,
                ToolStatus.AVAILABLE,
                now,
                now
        );
    }

    /**
     * Factoría para restaurar una herramienta existente desde la capa de persistencia.
     */
    public static Tool restore(
            ToolId id,
            UserId providerId,
            UUID categoryId,
            String name,
            String description,
            String brand,
            String model,
            Money dailyPrice,
            Money securityDeposit,
            ToolStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new Tool(
                id,
                providerId,
                categoryId,
                name,
                description,
                brand,
                model,
                dailyPrice,
                securityDeposit,
                status,
                createdAt,
                updatedAt
        );
    }

    public void updateDetails(
            String name,
            String description,
            String brand,
            String model,
            Money dailyPrice,
            Money securityDeposit,
            Instant now
    ) {
        this.name = validateNonBlank(name, "El nombre de la herramienta");
        this.description = description == null ? "" : description.trim();
        this.brand = validateNonBlank(brand, "La marca");
        this.model = validateNonBlank(model, "El modelo");
        this.dailyPrice = validatePositiveMoney(dailyPrice, "El precio diario");
        this.securityDeposit = validateZeroOrPositiveMoney(securityDeposit, "El depósito de garantía");
        this.updatedAt = Objects.requireNonNull(now, "La fecha de actualización no puede ser nula");
    }

    public void markAsRented(Instant now) {
        if (this.status != ToolStatus.AVAILABLE) {
            throw new InvalidToolStateException("Solo se puede rentar una herramienta que esté disponible. Estado actual: " + this.status);
        }
        this.status = ToolStatus.RENTED;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void markAsReturned(Instant now) {
        if (this.status != ToolStatus.RENTED) {
            throw new InvalidToolStateException("Solo se puede registrar retorno de una herramienta en renta. Estado actual: " + this.status);
        }
        this.status = ToolStatus.AVAILABLE;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void sendToMaintenance(Instant now) {
        if (this.status == ToolStatus.RENTED) {
            throw new InvalidToolStateException("No se puede enviar a mantenimiento una herramienta que está actualmente rentada");
        }
        this.status = ToolStatus.MAINTENANCE;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void restoreFromMaintenance(Instant now) {
        if (this.status != ToolStatus.MAINTENANCE) {
            throw new InvalidToolStateException("La herramienta no se encuentra en mantenimiento");
        }
        this.status = ToolStatus.AVAILABLE;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void deactivate(Instant now) {
        if (this.status == ToolStatus.RENTED) {
            throw new InvalidToolStateException("No se puede inactivar una herramienta en renta");
        }
        this.status = ToolStatus.INACTIVE;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public boolean isAvailable() {
        return this.status == ToolStatus.AVAILABLE;
    }

    public UserId getProviderId() {
        return providerId;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Money getDailyPrice() {
        return dailyPrice;
    }

    public Money getSecurityDeposit() {
        return securityDeposit;
    }

    public ToolStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    private static String validateNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " no puede ser nulo");
        String trimmed = value.trim();
        if (trimmed.isBlank()) {
            throw new InvalidToolDataException(fieldName + " no puede estar vacío");
        }
        return trimmed;
    }

    private static Money validatePositiveMoney(Money money, String fieldName) {
        Objects.requireNonNull(money, fieldName + " no puede ser nulo");
        if (!money.isPositive()) {
            throw new InvalidToolDataException(fieldName + " debe ser un monto estrictamente positivo");
        }
        return money;
    }

    private static Money validateZeroOrPositiveMoney(Money money, String fieldName) {
        Objects.requireNonNull(money, fieldName + " no puede ser nulo");
        if (!money.isZeroOrPositive()) {
            throw new InvalidToolDataException(fieldName + " no puede ser negativo");
        }
        return money;
    }

    public static class InvalidToolStateException extends DomainException {
        public InvalidToolStateException(String message) {
            super(message);
        }
    }

    public static class InvalidToolDataException extends DomainException {
        public InvalidToolDataException(String message) {
            super(message);
        }
    }
}
