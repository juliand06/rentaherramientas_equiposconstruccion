package com.rentatools.application.catalog.dto;

import com.rentatools.domain.catalog.model.aggregate.Tool;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/** Vista de lectura de una herramienta. Evita exponer el agregado fuera de la aplicación. */
public record ToolDto(
        UUID id,
        UUID providerId,
        UUID categoryId,
        String code,
        String name,
        String description,
        String brand,
        String model,
        BigDecimal dailyRentalRate,
        BigDecimal securityDeposit,
        BigDecimal replacementValue,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {

    public static ToolDto from(Tool tool) {
        return new ToolDto(
                tool.getId().value(),
                tool.getProviderId().value(),
                tool.getCategoryId().value(),
                tool.getCode(),
                tool.getName(),
                tool.getDescription(),
                tool.getBrand(),
                tool.getModel(),
                tool.getDailyRentalRate().amount(),
                tool.getSecurityDeposit().amount(),
                tool.getReplacementValue().amount(),
                tool.isActive(),
                tool.getCreatedAt(),
                tool.getUpdatedAt());
    }
}
