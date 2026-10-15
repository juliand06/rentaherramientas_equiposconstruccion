package com.rentatools.application.catalog.usecase;

import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.port.repository.ToolRepository;

import java.util.List;
import java.util.UUID;

/** Lista las herramientas activas, opcionalmente filtradas por categoría. */
public class ListToolsUseCase {

    private final ToolRepository toolRepository;

    public ListToolsUseCase(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<ToolDto> execute(UUID categoryId) {
        var tools = categoryId == null
                ? toolRepository.findAllActive()
                : toolRepository.findActiveByCategoryId(CategoryId.of(categoryId));
        return tools.stream().map(ToolDto::from).toList();
    }
}
