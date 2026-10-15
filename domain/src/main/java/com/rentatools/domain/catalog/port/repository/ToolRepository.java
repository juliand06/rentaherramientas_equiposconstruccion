package com.rentatools.domain.catalog.port.repository;

import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.model.valueobject.ToolId;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida del dominio para persistir el agregado {@link Tool}.
 * Sin dependencias de frameworks ni de JPA.
 */
public interface ToolRepository {

    Tool save(Tool tool);

    Optional<Tool> findById(ToolId id);

    List<Tool> findAllActive();

    List<Tool> findActiveByCategoryId(CategoryId categoryId);

    boolean existsByCode(String code);
}
