package com.rentatools.infrastructure.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Estructura estándar de error según la guía de excepciones del profesor.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> fieldErrors,
        List<String> details
) {

    public static ApiErrorResponse of(int status, String error, String message, String path) {
        return new ApiErrorResponse(Instant.now(), status, error, message, path, null, null);
    }

    public static ApiErrorResponse withFieldErrors(int status, String error, String message, String path, Map<String, String> fieldErrors) {
        return new ApiErrorResponse(Instant.now(), status, error, message, path, fieldErrors, null);
    }

    public static ApiErrorResponse withDetails(int status, String error, String message, String path, List<String> details) {
        return new ApiErrorResponse(Instant.now(), status, error, message, path, null, details);
    }
}
