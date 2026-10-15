package com.rentatools.application.identity.usecase;

import com.rentatools.application.identity.dto.UserDto;
import com.rentatools.application.identity.exception.UserNotFoundException;
import com.rentatools.domain.identity.model.valueobject.UserId;
import com.rentatools.domain.identity.port.repository.UserRepository;

import java.util.UUID;

public class GetUserProfileUseCase {

    private final UserRepository userRepository;

    public GetUserProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto execute(UUID userId) {
        return userRepository.findById(UserId.of(userId))
                .map(UserDto::from)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
