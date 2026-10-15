package com.rentatools.domain.catalog.model.aggregate;

import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.ProviderId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ToolAggregateTest {

    private final Instant now = Instant.parse("2026-10-15T12:00:00Z");

    private Tool newTool(Money dailyRate, Money deposit, Money replacement) {
        return Tool.create(ProviderId.generate(), CategoryId.generate(), "DEM-001",
                "Martillo Demoledor 15 Kg", "Martillo electro-neumático de alto impacto",
                "Bosch", "GSH 16-28", dailyRate, deposit, replacement, now);
    }

    private Tool validTool() {
        return newTool(Money.of(new BigDecimal("85000")), Money.of(new BigDecimal("300000")),
                Money.of(new BigDecimal("3500000")));
    }

    @Test
    @DisplayName("Crea una herramienta activa con datos válidos")
    void shouldCreateActiveTool() {
        Tool tool = validTool();

        assertNotNull(tool.getId());
        assertEquals("DEM-001", tool.getCode());
        assertTrue(tool.isActive());
        assertEquals(now, tool.getCreatedAt());
    }

    @Test
    @DisplayName("Rechaza una tarifa diaria menor o igual a cero")
    void shouldRejectNonPositiveDailyRate() {
        assertThrows(Tool.InvalidToolDataException.class, () ->
                newTool(Money.of(BigDecimal.ZERO), Money.of(new BigDecimal("1000")), Money.of(new BigDecimal("1000"))));
    }

    @Test
    @DisplayName("Rechaza un depósito negativo y un valor de reposición no positivo")
    void shouldRejectInvalidDepositAndReplacement() {
        assertThrows(Tool.InvalidToolDataException.class, () ->
                newTool(Money.of(new BigDecimal("1000")), Money.of(new BigDecimal("-1")), Money.of(new BigDecimal("1000"))));
        assertThrows(Tool.InvalidToolDataException.class, () ->
                newTool(Money.of(new BigDecimal("1000")), Money.of(BigDecimal.ZERO), Money.of(BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("Rechaza código vacío")
    void shouldRejectBlankCode() {
        assertThrows(Tool.InvalidToolDataException.class, () ->
                Tool.create(ProviderId.generate(), CategoryId.generate(), "  ", "Nombre", "Desc",
                        "Marca", "Modelo", Money.of(new BigDecimal("1000")), Money.ZERO,
                        Money.of(new BigDecimal("1000")), now));
    }

    @Test
    @DisplayName("Desactivar y activar respeta el estado actual")
    void shouldToggleActiveState() {
        Tool tool = validTool();
        Instant later = now.plusSeconds(60);

        tool.deactivate(later);
        assertFalse(tool.isActive());
        assertEquals(later, tool.getUpdatedAt());
        assertThrows(Tool.InvalidToolStateException.class, () -> tool.deactivate(later));

        tool.activate(later);
        assertTrue(tool.isActive());
        assertThrows(Tool.InvalidToolStateException.class, () -> tool.activate(later));
    }
}
