package com.rentatools.application.catalog;

import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.catalog.port.repository.ToolRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Fake en memoria del puerto: permite probar los casos de uso sin base de datos. */
class InMemoryToolRepository implements ToolRepository {

    private final Map<ToolId, Tool> store = new LinkedHashMap<>();

    @Override
    public Tool save(Tool tool) {
        store.put(tool.getId(), tool);
        return tool;
    }

    @Override
    public Optional<Tool> findById(ToolId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Tool> findAllActive() {
        return store.values().stream().filter(Tool::isActive).toList();
    }

    @Override
    public List<Tool> findActiveByCategoryId(CategoryId categoryId) {
        return findAllActive().stream().filter(t -> t.getCategoryId().equals(categoryId)).toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return store.values().stream().anyMatch(t -> t.getCode().equals(code));
    }
}
