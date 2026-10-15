package com.rentatools.domain.catalog.port.repository;

import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.identity.model.valueobject.UserId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida del dominio para la persistencia del agregado Tool.
 * Solo define operaciones del negocio, sin dependencias de frameworks ni JPA.
 */
public interface ToolRepository {

    Tool save(Tool tool);

    Optional<Tool> findById(ToolId id);

    List<Tool> findAllAvailable();

    List<Tool> findByCategoryId(UUID categoryId);

    List<Tool> findByProviderId(UserId providerId);

    boolean existsById(ToolId id);
}
