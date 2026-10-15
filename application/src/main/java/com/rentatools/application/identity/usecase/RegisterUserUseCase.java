package com.rentatools.application.identity.usecase;

import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.application.identity.command.RegisterUserCommand;
import com.rentatools.application.identity.dto.UserDto;
import com.rentatools.application.identity.exception.DuplicateUserEmailException;
import com.rentatools.application.identity.port.PasswordEncoderPort;
import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.aggregate.User;
import com.rentatools.domain.identity.port.repository.UserRepository;

public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final TimeProvider timeProvider;

    public RegisterUserUseCase(UserRepository userRepository, PasswordEncoderPort passwordEncoder, TimeProvider timeProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.timeProvider = timeProvider;
    }

    public UserDto execute(RegisterUserCommand command) {
        Email email = Email.of(command.email());
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateUserEmailException(email.value());
        }
        String passwordHash = passwordEncoder.encode(command.rawPassword());
        User user = User.register(
                email,
                passwordHash,
                command.firstName(),
                command.lastName(),
                command.role(),
                timeProvider.now()
        );
        return UserDto.from(userRepository.save(user));
    }
}
