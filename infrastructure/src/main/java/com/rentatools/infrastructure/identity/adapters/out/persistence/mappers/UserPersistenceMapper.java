package com.rentatools.infrastructure.identity.adapters.out.persistence.mappers;

import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.aggregate.User;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.RoleJpaEntity;
import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.UserJpaEntity;

import java.util.Set;
import java.util.stream.Collectors;

public final class UserPersistenceMapper {

    private UserPersistenceMapper() {}

    public static User toDomain(UserJpaEntity entity) {
        Set<User.RoleName> roles = entity.getRoles().stream()
                .map(r -> User.RoleName.valueOf(r.getName()))
                .collect(Collectors.toSet());

        return User.restore(
                UserId.of(entity.getId()),
                Email.of(entity.getEmail()),
                entity.getPasswordHash(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.isActive(),
                roles,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserJpaEntity toEntity(User user, Set<RoleJpaEntity> roles) {
        return new UserJpaEntity(
                user.getId().value(),
                user.getEmail().value(),
                user.getPasswordHash(),
                user.getFirstName(),
                user.getLastName(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                roles
        );
    }
}
