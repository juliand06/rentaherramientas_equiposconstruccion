package com.rentatools.application.identity;

import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.aggregate.User;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.identity.port.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private final Map<UserId, User> store = new LinkedHashMap<>();

    @Override
    public User save(User user) {
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return store.values().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    @Override
    public boolean existsByEmail(Email email) {
        return store.values().stream().anyMatch(u -> u.getEmail().equals(email));
    }
}
