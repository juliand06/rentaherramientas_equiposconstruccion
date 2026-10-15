package com.rentatools.infrastructure.catalog.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.catalog.adapters.out.persistence.entity.ToolJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ToolJpaRepository extends JpaRepository<ToolJpaEntity, UUID> {

    List<ToolJpaEntity> findByActiveTrue();

    List<ToolJpaEntity> findByCategoryIdAndActiveTrue(UUID categoryId);

    boolean existsByCode(String code);
}
