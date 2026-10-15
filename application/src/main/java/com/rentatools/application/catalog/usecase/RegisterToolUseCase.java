package com.rentatools.application.catalog.usecase;

import com.rentatools.application.catalog.command.RegisterToolCommand;
import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.exception.DuplicateToolCodeException;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.port.repository.ToolRepository;
import com.rentatools.domain.common.valueobject.Money;
import com.rentatools.domain.identity.model.valueobject.ProviderId;

/** Registra una herramienta nueva. Sin anotaciones de Spring: la crea {@code CatalogBeansConfig}. */
public class RegisterToolUseCase {

    private final ToolRepository toolRepository;
    private final TimeProvider timeProvider;

    public RegisterToolUseCase(ToolRepository toolRepository, TimeProvider timeProvider) {
        this.toolRepository = toolRepository;
        this.timeProvider = timeProvider;
    }

    public ToolDto execute(RegisterToolCommand command) {
        String code = command.code() == null ? "" : command.code().trim();
        if (toolRepository.existsByCode(code)) {
            throw new DuplicateToolCodeException(code);
        }
        Tool tool = Tool.create(
                ProviderId.of(command.providerId()),
                CategoryId.of(command.categoryId()),
                code,
                command.name(),
                command.description(),
                command.brand(),
                command.model(),
                Money.of(command.dailyRentalRate()),
                Money.of(command.securityDeposit()),
                Money.of(command.replacementValue()),
                timeProvider.now());
        return ToolDto.from(toolRepository.save(tool));
    }
}
