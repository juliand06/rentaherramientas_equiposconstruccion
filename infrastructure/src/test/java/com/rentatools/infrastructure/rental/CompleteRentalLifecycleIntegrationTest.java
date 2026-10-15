package com.rentatools.infrastructure.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rentatools.application.billing.command.ProcessPaymentCommand;
import com.rentatools.application.billing.dto.PaymentDto;
import com.rentatools.application.billing.usecase.GetPaymentUseCase;
import com.rentatools.application.billing.usecase.ProcessPaymentUseCase;
import com.rentatools.application.catalog.command.RegisterToolCommand;
import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.usecase.GetToolUseCase;
import com.rentatools.application.catalog.usecase.ListToolsUseCase;
import com.rentatools.application.catalog.usecase.RegisterToolUseCase;
import com.rentatools.application.rental.command.CreateReservationCommand;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.usecase.ConfirmReservationUseCase;
import com.rentatools.application.rental.usecase.CreateReservationUseCase;
import com.rentatools.application.rental.usecase.GetReservationUseCase;
import com.rentatools.application.reporting.dto.SystemMetricsReportDto;
import com.rentatools.application.reporting.usecase.GetSystemMetricsUseCase;
import com.rentatools.domain.billing.model.aggregate.Payment;
import com.rentatools.infrastructure.billing.adapters.in.rest.controllers.PaymentController;
import com.rentatools.infrastructure.billing.adapters.in.rest.dtos.ProcessPaymentRequest;
import com.rentatools.infrastructure.catalog.adapters.in.rest.controllers.ToolController;
import com.rentatools.infrastructure.catalog.adapters.in.rest.dtos.RegisterToolRequest;
import com.rentatools.infrastructure.common.exception.GlobalExceptionHandler;
import com.rentatools.infrastructure.rental.adapters.in.rest.controllers.ReservationController;
import com.rentatools.infrastructure.rental.adapters.in.rest.dtos.CreateReservationRequest;
import com.rentatools.infrastructure.reporting.adapters.in.rest.controllers.ReportingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Fase 9 — Prueba de Integración: Ciclo Completo de Reserva, Pago y Facturación")
class CompleteRentalLifecycleIntegrationTest {

    private MockMvc toolMockMvc;
    private MockMvc reservationMockMvc;
    private MockMvc paymentMockMvc;
    private MockMvc reportingMockMvc;

    private RegisterToolUseCase registerToolUseCase;
    private GetToolUseCase getToolUseCase;
    private ListToolsUseCase listToolsUseCase;

    private CreateReservationUseCase createReservationUseCase;
    private ConfirmReservationUseCase confirmReservationUseCase;
    private GetReservationUseCase getReservationUseCase;

    private ProcessPaymentUseCase processPaymentUseCase;
    private GetPaymentUseCase getPaymentUseCase;

    private GetSystemMetricsUseCase getSystemMetricsUseCase;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    void setUp() {
        registerToolUseCase = mock(RegisterToolUseCase.class);
        getToolUseCase = mock(GetToolUseCase.class);
        listToolsUseCase = mock(ListToolsUseCase.class);

        createReservationUseCase = mock(CreateReservationUseCase.class);
        confirmReservationUseCase = mock(ConfirmReservationUseCase.class);
        getReservationUseCase = mock(GetReservationUseCase.class);

        processPaymentUseCase = mock(ProcessPaymentUseCase.class);
        getPaymentUseCase = mock(GetPaymentUseCase.class);

        getSystemMetricsUseCase = mock(GetSystemMetricsUseCase.class);

        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

        toolMockMvc = MockMvcBuilders.standaloneSetup(new ToolController(registerToolUseCase, getToolUseCase, listToolsUseCase))
                .setControllerAdvice(exceptionHandler)
                .build();

        reservationMockMvc = MockMvcBuilders.standaloneSetup(new ReservationController(createReservationUseCase, confirmReservationUseCase, getReservationUseCase))
                .setControllerAdvice(exceptionHandler)
                .build();

        paymentMockMvc = MockMvcBuilders.standaloneSetup(new PaymentController(processPaymentUseCase, getPaymentUseCase))
                .setControllerAdvice(exceptionHandler)
                .build();

        reportingMockMvc = MockMvcBuilders.standaloneSetup(new ReportingController(getSystemMetricsUseCase))
                .setControllerAdvice(exceptionHandler)
                .build();
    }

    @Test
    @DisplayName("Flujo completo: Catálogo -> Reserva -> Pago -> Confirmación -> Métricas BI")
    void testCompleteRentalEndToEndFlow() throws Exception {
        UUID providerId = UUID.randomUUID();
        UUID categoryId = UUID.randomUUID();
        UUID toolId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID reservationId = UUID.randomUUID();
        UUID paymentId = UUID.randomUUID();
        Instant now = Instant.now();

        // 1. Catálogo: Registrar nueva herramienta
        ToolDto registeredTool = new ToolDto(
                toolId, providerId, categoryId, "EXC-001",
                "Excavadora Hidráulica CAT 320", "Maquinaria pesada",
                "Caterpillar", "320D",
                BigDecimal.valueOf(450000), BigDecimal.valueOf(1500000), BigDecimal.valueOf(180000000),
                true, now, now
        );
        when(registerToolUseCase.execute(any(RegisterToolCommand.class))).thenReturn(registeredTool);

        RegisterToolRequest toolRequest = new RegisterToolRequest(
                providerId, categoryId, "EXC-001", "Excavadora Hidráulica CAT 320",
                "Maquinaria pesada", "Caterpillar", "320D",
                BigDecimal.valueOf(450000), BigDecimal.valueOf(1500000), BigDecimal.valueOf(180000000)
        );

        toolMockMvc.perform(post("/api/tools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toolRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(toolId.toString()))
                .andExpect(jsonPath("$.code").value("EXC-001"))
                .andExpect(jsonPath("$.active").value(true));

        // 2. Catálogo: Listar herramientas disponibles
        when(listToolsUseCase.execute(null)).thenReturn(List.of(registeredTool));

        toolMockMvc.perform(get("/api/tools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Excavadora Hidráulica CAT 320"));

        // 3. Reserva: Cliente solicita reserva
        LocalDate startDate = LocalDate.now().plusDays(2);
        LocalDate endDate = LocalDate.now().plusDays(7);

        ReservationDto requestedReservation = new ReservationDto(
                reservationId, clientId, toolId, startDate, endDate, 5L,
                BigDecimal.valueOf(450000), BigDecimal.valueOf(2250000), BigDecimal.valueOf(1500000),
                "REQUESTED", now, now
        );
        when(createReservationUseCase.execute(any(CreateReservationCommand.class))).thenReturn(requestedReservation);

        CreateReservationRequest reservationRequest = new CreateReservationRequest(
                clientId, toolId, startDate, endDate,
                BigDecimal.valueOf(450000), BigDecimal.valueOf(1500000)
        );

        reservationMockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(reservationId.toString()))
                .andExpect(jsonPath("$.status").value("REQUESTED"))
                .andExpect(jsonPath("$.totalRentalAmount").value(2250000))
                .andExpect(jsonPath("$.depositAmount").value(1500000));

        // 4. Pago: Procesar depósito en garantía
        PaymentDto completedPayment = new PaymentDto(
                paymentId, reservationId, "TX-2026-9901", "CREDIT_CARD",
                BigDecimal.valueOf(3750000), "COMPLETED", now, now
        );
        when(processPaymentUseCase.execute(any(ProcessPaymentCommand.class))).thenReturn(completedPayment);

        ProcessPaymentRequest paymentRequest = new ProcessPaymentRequest(
                reservationId, Payment.PaymentMethod.CREDIT_CARD, BigDecimal.valueOf(3750000)
        );

        paymentMockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(paymentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(paymentId.toString()))
                .andExpect(jsonPath("$.status").value("COMPLETED"));

        // 5. Confirmación: Confirmar reserva y activar emisión de contrato
        ReservationDto confirmedReservation = new ReservationDto(
                reservationId, clientId, toolId, startDate, endDate, 5L,
                BigDecimal.valueOf(450000), BigDecimal.valueOf(2250000), BigDecimal.valueOf(1500000),
                "CONFIRMED", now, now
        );
        when(confirmReservationUseCase.execute(reservationId)).thenReturn(confirmedReservation);

        reservationMockMvc.perform(put("/api/reservations/{id}/confirm", reservationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMED"));

        // 6. Métricas: Consultar el reporte BI
        SystemMetricsReportDto metricsReport = new SystemMetricsReportDto(
                12L, 18L, 5L, BigDecimal.valueOf(48720000),
                Map.of("EARTHWORK", 8L, "DEMOLITION", 6L),
                Map.of("CONFIRMED", 3L, "DELIVERED", 2L)
        );
        when(getSystemMetricsUseCase.execute()).thenReturn(metricsReport);

        reportingMockMvc.perform(get("/api/reports/metrics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTools").value(18))
                .andExpect(jsonPath("$.totalReservations").value(5))
                .andExpect(jsonPath("$.totalRevenue").value(48720000));
    }

    @Test
    @DisplayName("Búsqueda de herramienta inexistente debe retornar 404 Not Found")
    void testToolNotFoundReturns404() throws Exception {
        UUID unknownId = UUID.randomUUID();
        when(getToolUseCase.execute(unknownId)).thenThrow(new com.rentatools.application.catalog.exception.ToolNotFoundException(unknownId));

        toolMockMvc.perform(get("/api/tools/{id}", unknownId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Recurso No Encontrado"))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    @DisplayName("Petición con datos inválidos de reserva debe retornar 400 Bad Request")
    void testInvalidReservationRequestReturns400() throws Exception {
        // Objeto JSON con campos nulos que violan las anotaciones de validación
        String invalidJson = """
            {
                "clientId": null,
                "toolId": null,
                "startDate": null,
                "endDate": null,
                "dailyRate": -100,
                "depositAmount": -500
            }
            """;

        reservationMockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }
}
