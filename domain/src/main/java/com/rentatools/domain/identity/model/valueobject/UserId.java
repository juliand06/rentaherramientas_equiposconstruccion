package com.rentatools.domain.identity.model.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Identificador fuertemente tipado para el agregado User.
 */
public record UserId(UUID value) implements Serializable {

    public UserId {
        Objects.requireNonNull(value, "El identificador del usuario no puede ser nulo");
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId of(String value) {
        try {
            return new UserId(UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            throw new InvalidUserIdException("UUID de usuario inválido: " + value);
        }
    }

    public static class InvalidUserIdException extends DomainException {
        public InvalidUserIdException(String message) {
            super(message);
        }
    }
}
