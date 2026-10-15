package com.rentatools.infrastructure.identity.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.RoleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleJpaRepository extends JpaRepository<RoleJpaEntity, UUID> {
    Optional<RoleJpaEntity> findByName(String name);
}
