package com.rentatools.application.catalog.usecase;

import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.exception.ToolNotFoundException;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.catalog.port.repository.ToolRepository;

import java.util.UUID;

/** Consulta una herramienta por su identificador. */
public class GetToolUseCase {

    private final ToolRepository toolRepository;

    public GetToolUseCase(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public ToolDto execute(UUID id) {
        return toolRepository.findById(ToolId.of(id))
                .map(ToolDto::from)
                .orElseThrow(() -> new ToolNotFoundException(id));
    }
}
