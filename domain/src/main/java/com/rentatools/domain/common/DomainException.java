package com.rentatools.domain.common;

/**
 * Excepción base para todas las infracciones de reglas de negocio e invariantes del dominio.
 * No extiende de RuntimeException de ningún framework específico; es Java puro.
 */
public abstract class DomainException extends RuntimeException {
    
    protected DomainException(String message) {
        super(message);
    }

    protected DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
