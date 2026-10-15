package com.rentatools.application.catalog;

import com.rentatools.application.catalog.command.RegisterToolCommand;
import com.rentatools.application.catalog.dto.ToolDto;
import com.rentatools.application.catalog.exception.DuplicateToolCodeException;
import com.rentatools.application.catalog.exception.ToolNotFoundException;
import com.rentatools.application.catalog.usecase.GetToolUseCase;
import com.rentatools.application.catalog.usecase.ListToolsUseCase;
import com.rentatools.application.catalog.usecase.RegisterToolUseCase;
import com.rentatools.domain.catalog.model.aggregate.Tool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ToolUseCasesTest {

    private static final Instant NOW = Instant.parse("2026-10-15T12:00:00Z");

    private InMemoryToolRepository repository;
    private RegisterToolUseCase register;
    private GetToolUseCase get;
    private ListToolsUseCase list;

    @BeforeEach
    void setUp() {
        repository = new InMemoryToolRepository();
        register = new RegisterToolUseCase(repository, () -> NOW);
        get = new GetToolUseCase(repository);
        list = new ListToolsUseCase(repository);
    }

    private RegisterToolCommand command(String code, UUID categoryId) {
        return new RegisterToolCommand(UUID.randomUUID(), categoryId, code, "Martillo", "Descripción",
                "Bosch", "GSH", new BigDecimal("85000"), new BigDecimal("300000"), new BigDecimal("3500000"));
    }

    @Test
    @DisplayName("Registrar guarda la herramienta con la hora del proveedor de tiempo")
    void registersTool() {
        ToolDto dto = register.execute(command("DEM-001", UUID.randomUUID()));

        assertEquals("DEM-001", dto.code());
        assertTrue(dto.active());
        assertEquals(NOW, dto.createdAt());
        assertEquals(dto.id(), get.execute(dto.id()).id());
    }

    @Test
    @DisplayName("No permite dos herramientas con el mismo código")
    void rejectsDuplicateCode() {
        register.execute(command("DEM-001", UUID.randomUUID()));

        assertThrows(DuplicateToolCodeException.class,
                () -> register.execute(command("DEM-001", UUID.randomUUID())));
    }

    @Test
    @DisplayName("Una regla de dominio violada se propaga como excepción de dominio")
    void propagatesDomainRule() {
        var invalid = new RegisterToolCommand(UUID.randomUUID(), UUID.randomUUID(), "X-1", "N", "D", "B", "M",
                BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.TEN);

        assertThrows(Tool.InvalidToolDataException.class, () -> register.execute(invalid));
    }

    @Test
    @DisplayName("Consultar un id inexistente lanza ToolNotFoundException")
    void failsWhenNotFound() {
        assertThrows(ToolNotFoundException.class, () -> get.execute(UUID.randomUUID()));
    }

    @Test
    @DisplayName("Listar filtra por categoría y omite herramientas inactivas")
    void listsByCategory() {
        UUID cat1 = UUID.randomUUID();
        UUID cat2 = UUID.randomUUID();
        register.execute(command("A-1", cat1));
        register.execute(command("A-2", cat1));
        ToolDto other = register.execute(command("B-1", cat2));

        assertEquals(3, list.execute(null).size());
        List<ToolDto> onlyCat1 = list.execute(cat1);
        assertEquals(2, onlyCat1.size());
        assertTrue(onlyCat1.stream().allMatch(t -> t.categoryId().equals(cat1)));

        repository.findById(com.rentatools.domain.catalog.model.valueobject.ToolId.of(other.id()))
                .orElseThrow().deactivate(NOW);
        assertEquals(2, list.execute(null).size());
    }
}
