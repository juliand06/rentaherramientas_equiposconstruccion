package com.rentatools.domain.common.valueobject;

import com.rentatools.domain.common.DomainException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value Object inmutable que representa una cantidad monetaria con su divisa.
 * Garantiza precisión decimal para operaciones financieras (COP o USD).
 */
public record Money(BigDecimal amount, String currency) implements Serializable, Comparable<Money> {

    public static final String DEFAULT_CURRENCY = "COP";
    public static final Money ZERO = new Money(BigDecimal.ZERO, DEFAULT_CURRENCY);

    public Money {
        Objects.requireNonNull(amount, "El monto no puede ser nulo");
        Objects.requireNonNull(currency, "La divisa no puede ser nula");
        if (currency.isBlank()) {
            throw new InvalidMoneyException("La divisa no puede estar vacía");
        }
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount, DEFAULT_CURRENCY);
    }

    public static Money of(double amount) {
        return new Money(BigDecimal.valueOf(amount), DEFAULT_CURRENCY);
    }

    public static Money of(BigDecimal amount, String currency) {
        return new Money(amount, currency);
    }

    public Money add(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        assertSameCurrency(other);
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    public Money multiply(long factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }

    public Money multiply(BigDecimal factor) {
        Objects.requireNonNull(factor, "El factor multiplicador no puede ser nulo");
        return new Money(this.amount.multiply(factor), this.currency);
    }

    public boolean isPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isZeroOrPositive() {
        return this.amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    public boolean isGreaterThan(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount) > 0;
    }

    private void assertSameCurrency(Money other) {
        Objects.requireNonNull(other, "El valor a comparar no puede ser nulo");
        if (!this.currency.equalsIgnoreCase(other.currency)) {
            throw new InvalidMoneyException(String.format("No se pueden operar divisas distintas: %s y %s", this.currency, other.currency));
        }
    }

    @Override
    public int compareTo(Money other) {
        assertSameCurrency(other);
        return this.amount.compareTo(other.amount);
    }

    public static class InvalidMoneyException extends DomainException {
        public InvalidMoneyException(String message) {
            super(message);
        }
    }
}
