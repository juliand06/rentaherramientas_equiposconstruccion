package com.rentatools.infrastructure.catalog.adapters.in.rest.controllers;

import com.rentatools.application.catalog.command.RegisterToolCommand;
import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.usecase.GetToolUseCase;
import com.rentatools.application.catalog.usecase.ListToolsUseCase;
import com.rentatools.application.catalog.usecase.RegisterToolUseCase;
import com.rentatools.infrastructure.catalog.adapters.in.rest.dtos.RegisterToolRequest;
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

/** Controlador mínimo del catálogo: traduce request → command, llama al caso de uso, devuelve. Sin lógica. */
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToolDto register(@Valid @RequestBody RegisterToolRequest request) {
        return registerTool.execute(new RegisterToolCommand(
                request.providerId(), request.categoryId(), request.code(), request.name(),
                request.description(), request.brand(), request.model(), request.dailyRentalRate(),
                request.securityDeposit(), request.replacementValue()));
    }

    @GetMapping("/{id}")
    public ToolDto get(@PathVariable UUID id) {
        return getTool.execute(id);
    }

    @GetMapping
    public List<ToolDto> list(@RequestParam(required = false) UUID categoryId) {
        return listTools.execute(categoryId);
    }
}
