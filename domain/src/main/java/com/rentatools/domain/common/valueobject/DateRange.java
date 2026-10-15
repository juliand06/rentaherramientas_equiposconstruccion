package com.rentatools.domain.common.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Value Object inmutable que representa un rango de fechas de reserva o alquiler.
 * Aplica la invariante de que la fecha final debe ser igual o posterior a la fecha inicial.
 */
public record DateRange(LocalDate startDate, LocalDate endDate) implements Serializable {

    public DateRange {
        Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
        Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
        if (endDate.isBefore(startDate)) {
            throw new InvalidDateRangeException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public static DateRange of(LocalDate start, LocalDate end) {
        return new DateRange(start, end);
    }

    public long totalDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public boolean overlaps(DateRange other) {
        Objects.requireNonNull(other, "El rango de fechas no puede ser nulo");
        return !this.endDate.isBefore(other.startDate) && !this.startDate.isAfter(other.endDate);
    }

    public boolean contains(LocalDate date) {
        Objects.requireNonNull(date, "La fecha no puede ser nula");
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public static class InvalidDateRangeException extends DomainException {
        public InvalidDateRangeException(String message) {
            super(message);
        }
    }
}
