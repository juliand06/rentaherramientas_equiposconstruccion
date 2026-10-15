package com.rentatools.domain.catalog.model.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Identificador fuertemente tipado para la entidad/agregado Tool.
 */
public record ToolId(UUID value) implements Serializable {

    public ToolId {
        Objects.requireNonNull(value, "El identificador de la herramienta no puede ser nulo");
    }

    public static ToolId generate() {
        return new ToolId(UUID.randomUUID());
    }

    public static ToolId of(UUID value) {
        return new ToolId(value);
    }

    public static ToolId of(String value) {
        try {
            return new ToolId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw new InvalidToolIdException("UUID de herramienta inválido: " + value);
        }
    }

    public static class InvalidToolIdException extends DomainException {
        public InvalidToolIdException(String message) {
            super(message);
        }
    }
}
