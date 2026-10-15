package com.rentatools.application.common.exception;

/**
 * Excepción base de la capa de aplicación (casos de uso). Java puro, sin dependencias de Spring.
 * La infraestructura la traduce a una respuesta HTTP.
 */
public abstract class ApplicationException extends RuntimeException {

    protected ApplicationException(String message) {
        super(message);
    }
}
