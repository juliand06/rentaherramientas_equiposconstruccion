package com.rentatools.domain.common.valueobject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ValueObjectsTest {

    @Test
    @DisplayName("Money debe sumar y restar correctamente manteniendo precisión")
    void shouldHandleMoneyOperations() {
        Money m1 = Money.of(100.50);
        Money m2 = Money.of(50.25);

        Money sum = m1.add(m2);
        assertEquals(new BigDecimal("150.75"), sum.amount());
        assertEquals("COP", sum.currency());

        Money diff = m1.subtract(m2);
        assertEquals(new BigDecimal("50.25"), diff.amount());

        Money mult = m1.multiply(2);
        assertEquals(new BigDecimal("201.00"), mult.amount());
    }

    @Test
    @DisplayName("Money debe rechazar divisas diferentes")
    void shouldRejectDifferentCurrencies() {
        Money cop = Money.of(new BigDecimal("1000"), "COP");
        Money usd = Money.of(new BigDecimal("100"), "USD");

        assertThrows(Money.InvalidMoneyException.class, () -> cop.add(usd));
    }

    @Test
    @DisplayName("DateRange debe calcular total de días y detectar traslapes")
    void shouldValidateDateRange() {
        LocalDate start = LocalDate.of(2026, 10, 1);
        LocalDate end = LocalDate.of(2026, 10, 5);
        DateRange range1 = DateRange.of(start, end);

        assertEquals(5, range1.totalDays());

        DateRange overlapping = DateRange.of(LocalDate.of(2026, 10, 4), LocalDate.of(2026, 10, 8));
        assertTrue(range1.overlaps(overlapping));

        DateRange separate = DateRange.of(LocalDate.of(2026, 10, 6), LocalDate.of(2026, 10, 10));
        assertFalse(range1.overlaps(separate));
    }

    @Test
    @DisplayName("DateRange debe fallar si fin es anterior a inicio")
    void shouldFailWhenEndDateBeforeStart() {
        assertThrows(DateRange.InvalidDateRangeException.class, () ->
                DateRange.of(LocalDate.of(2026, 10, 5), LocalDate.of(2026, 10, 1)));
    }

    @Test
    @DisplayName("Email debe validar formato estándar")
    void shouldValidateEmail() {
        assertDoesNotThrow(() -> Email.of("usuario@rentatools.com"));
        assertThrows(Email.InvalidEmailException.class, () -> Email.of("correo-invalido"));
    }
}
