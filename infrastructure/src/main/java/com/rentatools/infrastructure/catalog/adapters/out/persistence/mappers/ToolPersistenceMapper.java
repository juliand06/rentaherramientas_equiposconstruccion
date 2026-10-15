package com.rentatools.infrastructure.catalog.adapters.out.persistence.mappers;

import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.ProviderId;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.entity.ToolJpaEntity;

/** Mapper manual entre el agregado {@link Tool} y su entidad JPA (sin MapStruct, a propósito). */
public final class ToolPersistenceMapper {

    private ToolPersistenceMapper() {
    }

    public static ToolJpaEntity toEntity(Tool tool) {
        return new ToolJpaEntity(
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

    public static Tool toDomain(ToolJpaEntity entity) {
        return Tool.restore(
                ToolId.of(entity.getId()),
                ProviderId.of(entity.getProviderId()),
                CategoryId.of(entity.getCategoryId()),
                entity.getCode(),
                entity.getName(),
                entity.getDescription(),
                entity.getBrand(),
                entity.getModel(),
                Money.of(entity.getDailyRentalRate()),
                Money.of(entity.getSecurityDepositAmount()),
                Money.of(entity.getReplacementValue()),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
