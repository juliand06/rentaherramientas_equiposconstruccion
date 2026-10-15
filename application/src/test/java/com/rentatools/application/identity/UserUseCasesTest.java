package com.rentatools.application.identity;

import com.rentatools.application.identity.command.RegisterUserCommand;
import com.rentatools.application.identity.dto.UserDto;
import com.rentatools.application.identity.exception.DuplicateUserEmailException;
import com.rentatools.application.identity.exception.UserNotFoundException;
import com.rentatools.application.identity.port.PasswordEncoderPort;
import com.rentatools.application.identity.usecase.GetUserProfileUseCase;
import com.rentatools.application.identity.usecase.RegisterUserUseCase;
import com.rentatools.domain.identity.model.aggregate.User.RoleName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserUseCasesTest {

    private static final Instant NOW = Instant.parse("2026-10-15T12:00:00Z");

    private InMemoryUserRepository repository;
    private RegisterUserUseCase register;
    private GetUserProfileUseCase get;

    @BeforeEach
    void setUp() {
        repository = new InMemoryUserRepository();
        PasswordEncoderPort fakeEncoder = new PasswordEncoderPort() {
            @Override
            public String encode(CharSequence rawPassword) {
                return "hash:" + rawPassword;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return ("hash:" + rawPassword).equals(encodedPassword);
            }
        };
        register = new RegisterUserUseCase(repository, fakeEncoder, () -> NOW);
        get = new GetUserProfileUseCase(repository);
    }

    @Test
    @DisplayName("Registra un usuario correctamente y encripta contraseña")
    void shouldRegisterUser() {
        RegisterUserCommand cmd = new RegisterUserCommand(
                "andres@rentatools.com",
                "secreto123",
                "Andrés",
                "Ricaurte",
                RoleName.CLIENT
        );

        UserDto dto = register.execute(cmd);

        assertNotNull(dto.id());
        assertEquals("andres@rentatools.com", dto.email());
        assertEquals("Andrés Ricaurte", dto.fullName());
        assertTrue(dto.roles().contains("CLIENT"));

        UserDto found = get.execute(dto.id());
        assertEquals(dto.id(), found.id());
    }

    @Test
    @DisplayName("Rechaza emails duplicados")
    void shouldRejectDuplicateEmail() {
        RegisterUserCommand cmd = new RegisterUserCommand(
                "duplicado@rentatools.com",
                "pass1",
                "Carlos",
                "Gómez",
                RoleName.PROVIDER
        );
        register.execute(cmd);

        assertThrows(DuplicateUserEmailException.class, () -> register.execute(cmd));
    }

    @Test
    @DisplayName("Lanza excepción si el usuario no existe")
    void shouldThrowWhenUserNotFound() {
        assertThrows(UserNotFoundException.class, () -> get.execute(UUID.randomUUID()));
    }
}
