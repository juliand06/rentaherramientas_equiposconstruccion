package com.rentatools.infrastructure.identity.adapters.out.persistence.repositories;

import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
