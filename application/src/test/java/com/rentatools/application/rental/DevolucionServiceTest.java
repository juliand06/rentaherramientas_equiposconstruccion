package com.rentatools.application.rental;

import com.rentatools.application.rental.dto.DevolucionDTO;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.service.DevolucionService;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.valueobject.DateRange;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.rental.model.aggregate.EstadoDevolucion;
import com.rentatools.domain.rental.model.aggregate.Reservation;
import com.rentatools.domain.rental.model.entity.ReporteDano;
import com.rentatools.domain.rental.model.valueobject.ReservationId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DevolucionServiceTest {

    private InMemoryReservationRepository reservationRepository;
    private InMemoryReporteDanoRepository reporteDanoRepository;
    private DevolucionService devolucionService;
    private Reservation testReservation;
    private final Instant now = Instant.parse("2026-10-26T10:00:00Z");

    @BeforeEach
    void setUp() {
        reservationRepository = new InMemoryReservationRepository();
        reporteDanoRepository = new InMemoryReporteDanoRepository();
        devolucionService = new DevolucionService(reservationRepository, reporteDanoRepository, () -> now);

        testReservation = Reservation.create(
                UserId.generate(),
                ToolId.generate(),
                DateRange.of(LocalDate.of(2026, 10, 20), LocalDate.of(2026, 10, 25)),
                Money.of(new BigDecimal("50000.00")),
                Money.of(new BigDecimal("200000.00")),
                now
        );
        testReservation.confirm(now);
        reservationRepository.save(testReservation);
    }

    @Test
    @DisplayName("Debe registrar devolución OK cambiando estado a DEVUELTO_OK")
    void shouldRegisterDevolucionOk() {
        DevolucionDTO dto = new DevolucionDTO("OK", null, null);

        ReservationDto result = devolucionService.registrarDevolucion(testReservation.getId().value(), dto);

        assertEquals("DEVUELTO_OK", result.estadoDevolucion());
        assertTrue(reporteDanoRepository.findAll().isEmpty());
    }

    @Test
    @DisplayName("Debe registrar devolución con daños, cambiar estado a DEVUELTO_CON_DAÑOS y guardar ReporteDano")
    void shouldRegisterDevolucionWithDamages() {
        DevolucionDTO dto = new DevolucionDTO("DAÑOS", "Mandril roto y cable cortado", 180000.0);

        ReservationDto result = devolucionService.registrarDevolucion(testReservation.getId().value(), dto);

        assertEquals("DEVUELTO_CON_DAÑOS", result.estadoDevolucion());

        List<ReporteDano> reportes = reporteDanoRepository.findByReservaId(testReservation.getId());
        assertEquals(1, reportes.size());
        ReporteDano reporte = reportes.getFirst();
        assertEquals("Mandril roto y cable cortado", reporte.getDescripcion());
        assertEquals(180000.0, reporte.getCostoReparacion());
        assertEquals(now, reporte.getFechaReporte());
    }

    @Test
    @DisplayName("Debe admitir método registradorDevolucion con Long")
    void shouldSupportRegistradorDevolucionWithLong() {
        DevolucionDTO dto = new DevolucionDTO("DAÑOS", "Golpe en carcasa", 75000.0);

        ReservationDto result = devolucionService.registradorDevolucion(1L, dto);

        assertEquals("DEVUELTO_CON_DAÑOS", result.estadoDevolucion());
        assertFalse(reporteDanoRepository.findAll().isEmpty());
    }
}
