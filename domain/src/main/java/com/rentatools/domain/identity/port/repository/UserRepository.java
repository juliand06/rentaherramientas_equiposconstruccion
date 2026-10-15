package com.rentatools.domain.identity.port.repository;

import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.aggregate.User;
import com.rentatools.domain.identity.model.valueobject.UserId;

import java.util.Optional;

/**
 * Puerto de salida para persistencia de usuarios e identidad.
 */
public interface UserRepository {

    User save(User user);

    Optional<User> findById(UserId id);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);
}
