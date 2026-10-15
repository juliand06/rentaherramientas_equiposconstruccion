package com.rentatools.infrastructure.catalog.config;

import com.rentatools.application.catalog.usecase.GetToolUseCase;
import com.rentatools.application.catalog.usecase.ListToolsUseCase;
import com.rentatools.application.catalog.usecase.RegisterToolUseCase;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.domain.catalog.port.repository.ToolRepository;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.ToolRepositoryAdapter;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.repositories.ToolJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

/**
 * Cableado del contexto catalog. Los casos de uso no llevan anotaciones de Spring:
 * se registran aquí como beans para mantener application libre de frameworks.
 */
@Configuration
public class CatalogBeansConfig {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public TimeProvider timeProvider(Clock clock) {
        return clock::instant;
    }

    @Bean
    public ToolRepository toolRepository(ToolJpaRepository jpaRepository) {
        return new ToolRepositoryAdapter(jpaRepository);
    }

    @Bean
    public RegisterToolUseCase registerToolUseCase(ToolRepository toolRepository, TimeProvider timeProvider) {
        return new RegisterToolUseCase(toolRepository, timeProvider);
    }

    @Bean
    public GetToolUseCase getToolUseCase(ToolRepository toolRepository) {
        return new GetToolUseCase(toolRepository);
    }

    @Bean
    public ListToolsUseCase listToolsUseCase(ToolRepository toolRepository) {
        return new ListToolsUseCase(toolRepository);
    }
}
