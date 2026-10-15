package com.rentatools.infrastructure.common;

import com.rentatools.application.catalog.exception.DuplicateToolCodeException;
import com.rentatools.application.catalog.exception.ToolNotFoundException;
import com.rentatools.domain.common.DomainException;
import com.rentatools.infrastructure.common.exception.ApiErrorResponse;
import com.rentatools.infrastructure.common.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private final MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/test");

    @Test
    @DisplayName("DomainException debe traducirse a 400 Bad Request")
    void shouldHandleDomainException() {
        DomainException ex = new DomainException("Regla de negocio no cumplida") {};
        ResponseEntity<ApiErrorResponse> response = handler.handleDomainException(ex, request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Regla de negocio no cumplida", response.getBody().message());
    }

    @Test
    @DisplayName("ToolNotFoundException debe traducirse a 404 Not Found")
    void shouldHandleNotFoundException() {
        ToolNotFoundException ex = new ToolNotFoundException(UUID.randomUUID());
        ResponseEntity<ApiErrorResponse> response = handler.handleNotFound(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("DuplicateToolCodeException debe traducirse a 409 Conflict")
    void shouldHandleConflictException() {
        DuplicateToolCodeException ex = new DuplicateToolCodeException("DEM-001");
        ResponseEntity<ApiErrorResponse> response = handler.handleConflict(ex, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
