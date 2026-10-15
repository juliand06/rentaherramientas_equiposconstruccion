package com.rentatools.infrastructure.common.exception;

import com.rentatools.application.catalog.exception.DuplicateToolCodeException;
import com.rentatools.application.catalog.exception.ToolNotFoundException;
import com.rentatools.application.common.exception.ApplicationException;
import com.rentatools.application.identity.exception.DuplicateUserEmailException;
import com.rentatools.application.identity.exception.UserNotFoundException;
import com.rentatools.application.rental.exception.ReservationNotFoundException;
import com.rentatools.domain.common.DomainException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones según la Guía teórico-práctica de Excepciones.
 * Traduce excepciones de negocio a códigos HTTP adecuados (400, 401, 403, 404, 409, 500).
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 400 Bad Request — Infracción de reglas de negocio en Domain
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiErrorResponse> handleDomainException(DomainException ex, HttpServletRequest request) {
        log.warn("Infracción de regla de negocio en dominio: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.of(HttpStatus.BAD_REQUEST.value(), "Regla de Negocio Inválida", ex.getMessage(), request.getRequestURI()));
    }

    // 404 Not Found — Entidad no encontrada en Application
    @ExceptionHandler({
            ToolNotFoundException.class,
            UserNotFoundException.class,
            ReservationNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFound(ApplicationException ex, HttpServletRequest request) {
        log.info("Recurso no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.of(HttpStatus.NOT_FOUND.value(), "Recurso No Encontrado", ex.getMessage(), request.getRequestURI()));
    }

    // 409 Conflict — Violación de unicidad
    @ExceptionHandler({
            DuplicateToolCodeException.class,
            DuplicateUserEmailException.class
    })
    public ResponseEntity<ApiErrorResponse> handleConflict(ApplicationException ex, HttpServletRequest request) {
        log.warn("Conflicto de unicidad detectado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiErrorResponse.of(HttpStatus.CONFLICT.value(), "Conflicto de Datos", ex.getMessage(), request.getRequestURI()));
    }

    // 400 Bad Request — Error de validación de Bean Validation (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        log.warn("Error de validación en petición {}: {}", request.getRequestURI(), errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.withFieldErrors(
                        HttpStatus.BAD_REQUEST.value(),
                        "Error de Validación",
                        "Uno o más campos enviados no cumplen con los requisitos",
                        request.getRequestURI(),
                        errors
                ));
    }

    // 401 Unauthorized — Credenciales inválidas
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
        log.warn("Fallo de autenticación en {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiErrorResponse.of(HttpStatus.UNAUTHORIZED.value(), "No Autorizado", "Credenciales de acceso incorrectas o token inválido", request.getRequestURI()));
    }

    // 403 Forbidden — Sin permisos suficientes
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        log.warn("Acceso denegado en {}: {}", request.getRequestURI(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ApiErrorResponse.of(HttpStatus.FORBIDDEN.value(), "Acceso Denegado", "No posee los privilegios necesarios para realizar esta operación", request.getRequestURI()));
    }

    // 500 Internal Server Error — Excepciones no controladas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        log.error("Error no controlado en {}: ", request.getRequestURI(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error Interno del Servidor", "Ocurrió un error inesperado. Por favor contacte al administrador.", request.getRequestURI()));
    }
}
