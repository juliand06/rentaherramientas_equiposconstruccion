package com.rentatools.infrastructure.identity.adapters.out.persistence;

import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.aggregate.User;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.identity.port.repository.UserRepository;
import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.RoleJpaEntity;
import com.rentatools.infrastructure.identity.adapters.out.persistence.mappers.UserPersistenceMapper;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.RoleJpaRepository;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.UserJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional(readOnly = true)
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        Set<RoleJpaEntity> roles = new HashSet<>();
        for (User.RoleName roleName : user.getRoles()) {
            roleJpaRepository.findByName(roleName.name()).ifPresent(roles::add);
        }
        var entity = UserPersistenceMapper.toEntity(user, roles);
        return UserPersistenceMapper.toDomain(userJpaRepository.save(entity));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return userJpaRepository.findById(id.value()).map(UserPersistenceMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userJpaRepository.findByEmail(email.value()).map(UserPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userJpaRepository.existsByEmail(email.value());
    }
}
