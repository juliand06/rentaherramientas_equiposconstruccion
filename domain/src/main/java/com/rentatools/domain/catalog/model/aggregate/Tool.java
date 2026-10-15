package com.rentatools.domain.catalog.model.aggregate;

import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.AggregateRoot;
import com.rentatools.domain.common.DomainException;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.ProviderId;

import java.time.Instant;
import java.util.Objects;

/**
 * Agregado Tool (ficha maestra de una herramienta del catálogo). Refleja la tabla {@code tools}.
 *
 * <p>La disponibilidad física (rentada, en mantenimiento) pertenece a las unidades de inventario,
 * no a la ficha: aquí solo se controla si la herramienta está activa en el catálogo.
 */
public class Tool extends AggregateRoot<ToolId> {

    private final ProviderId providerId;
    private final CategoryId categoryId;
    private final String code;
    private String name;
    private String description;
    private String brand;
    private String model;
    private Money dailyRentalRate;
    private Money securityDeposit;
    private Money replacementValue;
    private boolean active;
    private final Instant createdAt;
    private Instant updatedAt;

    private Tool(ToolId id, ProviderId providerId, CategoryId categoryId, String code, String name,
                 String description, String brand, String model, Money dailyRentalRate,
                 Money securityDeposit, Money replacementValue, boolean active,
                 Instant createdAt, Instant updatedAt) {
        super(id);
        this.providerId = Objects.requireNonNull(providerId, "El proveedor no puede ser nulo");
        this.categoryId = Objects.requireNonNull(categoryId, "La categoría no puede ser nula");
        this.code = requireText(code, "El código");
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.active = active;
        applyDetails(name, description, brand, model, dailyRentalRate, securityDeposit, replacementValue);
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
    }

    /** Crea una herramienta nueva, activa. La fecha se recibe: el dominio no consulta el reloj. */
    public static Tool create(ProviderId providerId, CategoryId categoryId, String code, String name,
                              String description, String brand, String model, Money dailyRentalRate,
                              Money securityDeposit, Money replacementValue, Instant now) {
        return new Tool(ToolId.generate(), providerId, categoryId, code, name, description, brand, model,
                dailyRentalRate, securityDeposit, replacementValue, true, now, now);
    }

    /** Reconstruye una herramienta desde la persistencia, sin reglas de creación. */
    public static Tool restore(ToolId id, ProviderId providerId, CategoryId categoryId, String code,
                               String name, String description, String brand, String model,
                               Money dailyRentalRate, Money securityDeposit, Money replacementValue,
                               boolean active, Instant createdAt, Instant updatedAt) {
        return new Tool(id, providerId, categoryId, code, name, description, brand, model,
                dailyRentalRate, securityDeposit, replacementValue, active, createdAt, updatedAt);
    }

    public void updateDetails(String name, String description, String brand, String model,
                              Money dailyRentalRate, Money securityDeposit, Money replacementValue,
                              Instant now) {
        applyDetails(name, description, brand, model, dailyRentalRate, securityDeposit, replacementValue);
        this.updatedAt = Objects.requireNonNull(now, "La fecha de actualización no puede ser nula");
    }

    public void deactivate(Instant now) {
        if (!active) {
            throw new InvalidToolStateException("La herramienta ya está inactiva");
        }
        this.active = false;
        this.updatedAt = Objects.requireNonNull(now, "La fecha de actualización no puede ser nula");
    }

    public void activate(Instant now) {
        if (active) {
            throw new InvalidToolStateException("La herramienta ya está activa");
        }
        this.active = true;
        this.updatedAt = Objects.requireNonNull(now, "La fecha de actualización no puede ser nula");
    }

    private void applyDetails(String name, String description, String brand, String model,
                              Money dailyRentalRate, Money securityDeposit, Money replacementValue) {
        this.name = requireText(name, "El nombre de la herramienta");
        this.description = requireText(description, "La descripción");
        this.brand = requireText(brand, "La marca");
        this.model = requireText(model, "El modelo");
        this.dailyRentalRate = requirePositive(dailyRentalRate, "La tarifa diaria");
        this.securityDeposit = requireZeroOrPositive(securityDeposit, "El depósito de garantía");
        this.replacementValue = requirePositive(replacementValue, "El valor de reposición");
    }

    public ProviderId getProviderId() { return providerId; }
    public CategoryId getCategoryId() { return categoryId; }
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public Money getDailyRentalRate() { return dailyRentalRate; }
    public Money getSecurityDeposit() { return securityDeposit; }
    public Money getReplacementValue() { return replacementValue; }
    public boolean isActive() { return active; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field + " no puede ser nulo");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new InvalidToolDataException(field + " no puede estar vacío");
        }
        return trimmed;
    }

    private static Money requirePositive(Money money, String field) {
        Objects.requireNonNull(money, field + " no puede ser nulo");
        if (!money.isPositive()) {
            throw new InvalidToolDataException(field + " debe ser mayor que cero");
        }
        return money;
    }

    private static Money requireZeroOrPositive(Money money, String field) {
        Objects.requireNonNull(money, field + " no puede ser nulo");
        if (!money.isZeroOrPositive()) {
            throw new InvalidToolDataException(field + " no puede ser negativo");
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
