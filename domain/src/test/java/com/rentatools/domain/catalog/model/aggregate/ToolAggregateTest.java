package com.rentatools.domain.catalog.model.aggregate;

import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ToolAggregateTest {

    private final UserId sampleProviderId = UserId.generate();
    private final UUID sampleCategoryId = UUID.randomUUID();
    private final Instant now = Instant.now();

    @Test
    @DisplayName("Debe crear una herramienta disponible con datos válidos")
    void shouldCreateToolSuccessfully() {
        Tool tool = Tool.create(
                sampleProviderId,
                sampleCategoryId,
                "Rotomartillo Industrial",
                "Rotomartillo SDS Max 1500W",
                "Bosch",
                "GBH 8-45 D",
                Money.of(new BigDecimal("75000.00")),
                Money.of(new BigDecimal("300000.00")),
                now
        );

        assertNotNull(tool.getId());
        assertEquals("Rotomartillo Industrial", tool.getName());
        assertTrue(tool.isAvailable());
        assertEquals(Tool.ToolStatus.AVAILABLE, tool.getStatus());
    }

    @Test
    @DisplayName("No debe permitir rentar una herramienta si no está disponible")
    void shouldThrowExceptionWhenRentingUnavailableTool() {
        Tool tool = Tool.create(
                sampleProviderId,
                sampleCategoryId,
                "Generador Eléctrico",
                "Generador a gasolina 5.5 kVA",
                "Honda",
                "EG 6500 CX",
                Money.of(new BigDecimal("120000.00")),
                Money.of(new BigDecimal("500000.00")),
                now
        );

        tool.markAsRented(now);
        assertEquals(Tool.ToolStatus.RENTED, tool.getStatus());

        // Intentar volver a rentar debe lanzar excepción de dominio
        assertThrows(Tool.InvalidToolStateException.class, () -> tool.markAsRented(now));
    }

    @Test
    @DisplayName("No debe permitir enviar a mantenimiento una herramienta en renta")
    void shouldThrowExceptionWhenMaintenanceOnRentedTool() {
        Tool tool = Tool.create(
                sampleProviderId,
                sampleCategoryId,
                "Andamio Certificado",
                "Sección de andamio multidireccional",
                "Layher",
                "Allround",
                Money.of(new BigDecimal("40000.00")),
                Money.of(new BigDecimal("200000.00")),
                now
        );

        tool.markAsRented(now);
        assertThrows(Tool.InvalidToolStateException.class, () -> tool.sendToMaintenance(now));
    }

    @Test
    @DisplayName("Debe rechazar precios diarios menores o iguales a cero")
    void shouldRejectInvalidDailyPrice() {
        assertThrows(Tool.InvalidToolDataException.class, () ->
                Tool.create(
                        sampleProviderId,
                        sampleCategoryId,
                        "Bomba de Agua",
                        "Bomba sumergible 2 pulgadas",
                        "Pedrollo",
                        "Top Multi",
                        Money.of(BigDecimal.ZERO),
                        Money.of(new BigDecimal("100000.00")),
                        now
                )
        );
    }
}
