package com.rentatools.infrastructure.catalog.adapters.in.rest.controllers;

import com.rentatools.application.catalog.command.RegisterToolCommand;
import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.usecase.GetToolUseCase;
import com.rentatools.application.catalog.usecase.ListToolsUseCase;
import com.rentatools.application.catalog.usecase.RegisterToolUseCase;
import com.rentatools.infrastructure.catalog.adapters.in.rest.dtos.RegisterToolRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/** Controlador del catálogo de herramientas y maquinaria de construcción */
@Tag(name = "Herramientas", description = "Catálogo de herramientas y maquinaria para alquiler")
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final RegisterToolUseCase registerTool;
    private final GetToolUseCase getTool;
    private final ListToolsUseCase listTools;

    public ToolController(RegisterToolUseCase registerTool, GetToolUseCase getTool, ListToolsUseCase listTools) {
        this.registerTool = registerTool;
        this.getTool = getTool;
        this.listTools = listTools;
    }

    @Operation(summary = "Registrar nueva herramienta", description = "Registra una nueva herramienta en el catálogo del proveedor", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToolDto register(@Valid @RequestBody RegisterToolRequest request) {
        return registerTool.execute(new RegisterToolCommand(
                request.providerId(), request.categoryId(), request.code(), request.name(),
                request.description(), request.brand(), request.model(), request.dailyRentalRate(),
                request.securityDeposit(), request.replacementValue()));
    }

    @Operation(summary = "Consultar herramienta por ID", description = "Obtiene los detalles completos de una herramienta del catálogo")
    @GetMapping("/{id}")
    public ToolDto get(@Parameter(description = "ID de la herramienta") @PathVariable UUID id) {
        return getTool.execute(id);
    }

    @Operation(summary = "Listar herramientas", description = "Lista todas las herramientas o filtra por categoría")
    @GetMapping
    public List<ToolDto> list(@Parameter(description = "ID de la categoría (opcional)") @RequestParam(required = false) UUID categoryId) {
        return listTools.execute(categoryId);
    }
}
