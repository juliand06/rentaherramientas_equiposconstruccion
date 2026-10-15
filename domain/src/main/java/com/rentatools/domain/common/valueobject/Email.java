package com.rentatools.domain.common.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object inmutable que encapsula una dirección de correo electrónico válida.
 */
public record Email(String value) implements Serializable {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$"
    );

    public Email {
        Objects.requireNonNull(value, "El correo electrónico no puede ser nulo");
        value = value.trim().toLowerCase();
        if (value.isBlank() || !EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailException("El formato del correo electrónico no es válido: " + value);
        }
    }

    public static Email of(String value) {
        return new Email(value);
    }

    public static class InvalidEmailException extends DomainException {
        public InvalidEmailException(String message) {
            super(message);
        }
    }
}
