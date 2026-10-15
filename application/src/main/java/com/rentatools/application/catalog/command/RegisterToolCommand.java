package com.rentatools.application.catalog.command;

import java.math.BigDecimal;
import java.util.UUID;

/** Datos de entrada para registrar una herramienta en el catálogo. */
public record RegisterToolCommand(
        UUID providerId,
        UUID categoryId,
        String code,
        String name,
        String description,
        String brand,
        String model,
        BigDecimal dailyRentalRate,
        BigDecimal securityDeposit,
        BigDecimal replacementValue
) {
}
