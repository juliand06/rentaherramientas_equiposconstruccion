package com.rentatools.domain.catalog.model.valueobject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Identificador fuertemente tipado de una categoría del catálogo.
 */
public record CategoryId(UUID value) implements Serializable {

    public CategoryId {
        Objects.requireNonNull(value, "El identificador de la categoría no puede ser nulo");
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }

    public static CategoryId of(UUID value) {
        return new CategoryId(value);
    }
}
