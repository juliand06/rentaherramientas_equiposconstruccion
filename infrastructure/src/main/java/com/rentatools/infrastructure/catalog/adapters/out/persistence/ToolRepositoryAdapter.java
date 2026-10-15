package com.rentatools.infrastructure.catalog.adapters.out.persistence;

import com.rentatools.domain.catalog.model.aggregate.Tool;
import com.rentatools.domain.catalog.model.valueobject.CategoryId;
import com.rentatools.domain.catalog.model.valueobject.ToolId;
import com.rentatools.domain.catalog.port.repository.ToolRepository;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.mappers.ToolPersistenceMapper;
import com.rentatools.infrastructure.catalog.adapters.out.persistence.repositories.ToolJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa el puerto del dominio con Spring Data JPA.
 * Las transacciones viven aquí, en infraestructura, y no en los casos de uso.
 */
@Transactional(readOnly = true)
public class ToolRepositoryAdapter implements ToolRepository {

    private final ToolJpaRepository jpaRepository;

    public ToolRepositoryAdapter(ToolJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    @Transactional
    public Tool save(Tool tool) {
        return ToolPersistenceMapper.toDomain(jpaRepository.save(ToolPersistenceMapper.toEntity(tool)));
    }

    @Override
    public Optional<Tool> findById(ToolId id) {
        return jpaRepository.findById(id.value()).map(ToolPersistenceMapper::toDomain);
    }

    @Override
    public List<Tool> findAllActive() {
        return jpaRepository.findByActiveTrue().stream().map(ToolPersistenceMapper::toDomain).toList();
    }

    @Override
    public List<Tool> findActiveByCategoryId(CategoryId categoryId) {
        return jpaRepository.findByCategoryIdAndActiveTrue(categoryId.value()).stream()
                .map(ToolPersistenceMapper::toDomain).toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return jpaRepository.existsByCode(code);
    }
}
