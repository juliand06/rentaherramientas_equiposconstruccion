package com.rentatools.infrastructure.rental;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentatools.application.rental.dto.DevolucionDTO;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.service.DevolucionService;
import com.rentatools.infrastructure.common.exception.GlobalExceptionHandler;
import com.rentatools.infrastructure.rental.adapters.in.rest.controllers.DevolucionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Examen 1.5H: Pruebas del Endpoint de Devolución y Reporte de Daños")
class DevolucionControllerTest {

    private MockMvc mockMvc;
    private DevolucionService devolucionService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        devolucionService = mock(DevolucionService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DevolucionController(devolucionService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /api/reservas/{id}/registrar-devolucion debe responder 200 con estado DEVUELTO_OK")
    void shouldRegisterDevolucionOk() throws Exception {
        UUID reservationId = UUID.randomUUID();
        DevolucionDTO dto = new DevolucionDTO("OK", null, null);

        ReservationDto returnedDto = new ReservationDto(
                reservationId, UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.now(), LocalDate.now().plusDays(3), 3,
                BigDecimal.valueOf(50000), BigDecimal.valueOf(150000), BigDecimal.valueOf(200000),
                "COMPLETED", "DEVUELTO_OK", Instant.now(), Instant.now()
        );

        when(devolucionService.registrarDevolucion(eq(reservationId.toString()), any(DevolucionDTO.class)))
                .thenReturn(returnedDto);

        mockMvc.perform(post("/api/reservas/{id}/registrar-devolucion", reservationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reservationId.toString()))
                .andExpect(jsonPath("$.estadoDevolucion").value("DEVUELTO_OK"));
    }

    @Test
    @DisplayName("POST /api/reservas/{id}/registrar-devolucion debe responder 200 con estado DEVUELTO_CON_DAÑOS")
    void shouldRegisterDevolucionWithDamages() throws Exception {
        UUID reservationId = UUID.randomUUID();
        DevolucionDTO dto = new DevolucionDTO("DAÑOS", "Falla motor por sobrecalentamiento", 250000.0);

        ReservationDto returnedDto = new ReservationDto(
                reservationId, UUID.randomUUID(), UUID.randomUUID(),
                LocalDate.now(), LocalDate.now().plusDays(5), 5,
                BigDecimal.valueOf(60000), BigDecimal.valueOf(300000), BigDecimal.valueOf(250000),
                "COMPLETED", "DEVUELTO_CON_DAÑOS", Instant.now(), Instant.now()
        );

        when(devolucionService.registrarDevolucion(eq(reservationId.toString()), any(DevolucionDTO.class)))
                .thenReturn(returnedDto);

        mockMvc.perform(post("/api/reservas/{id}/registrar-devolucion", reservationId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reservationId.toString()))
                .andExpect(jsonPath("$.estadoDevolucion").value("DEVUELTO_CON_DAÑOS"));
    }
}
