package com.rentatools.infrastructure.catalog.adapters.in.rest.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

/** Cuerpo de la petición para registrar una herramienta. Valida la forma; el negocio lo valida el dominio. */
public record RegisterToolRequest(
        @NotNull(message = "El proveedor es obligatorio") UUID providerId,
        @NotNull(message = "La categoría es obligatoria") UUID categoryId,
        @NotBlank(message = "El código es obligatorio") @Size(max = 50) String code,
        @NotBlank(message = "El nombre es obligatorio") @Size(max = 120) String name,
        @NotBlank(message = "La descripción es obligatoria") String description,
        @NotBlank(message = "La marca es obligatoria") @Size(max = 60) String brand,
        @NotBlank(message = "El modelo es obligatorio") @Size(max = 60) String model,
        @NotNull @DecimalMin(value = "0.01", message = "La tarifa diaria debe ser mayor que cero") BigDecimal dailyRentalRate,
        @NotNull @DecimalMin(value = "0.00", message = "El depósito no puede ser negativo") BigDecimal securityDeposit,
        @NotNull @DecimalMin(value = "0.01", message = "El valor de reposición debe ser mayor que cero") BigDecimal replacementValue
) {
}
