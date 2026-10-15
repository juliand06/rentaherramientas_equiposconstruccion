package com.rentatools.domain.identity.model.aggregate;

import com.rentatools.domain.common.valueobject.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserAggregateTest {

    private final Instant now = Instant.now();

    @Test
    @DisplayName("Debe registrar un nuevo usuario con rol por defecto y activo")
    void shouldRegisterUser() {
        User user = User.register(
                Email.of("cliente@obra.com"),
                "$2a$12$hashedpassword",
                "Juan Pérez",
                User.RoleName.CLIENT,
                now
        );

        assertNotNull(user.getId());
        assertEquals("Juan Pérez", user.getFullName());
        assertTrue(user.isActive());
        assertTrue(user.getRoles().contains(User.RoleName.CLIENT));
    }

    @Test
    @DisplayName("No debe permitir remover el único rol que tiene un usuario")
    void shouldPreventRemovingLastRole() {
        User user = User.register(
                Email.of("proveedor@rentatools.com"),
                "$2a$12$hashedpassword",
                "Ferretería Central",
                User.RoleName.PROVIDER,
                now
        );

        assertThrows(User.InvalidUserDataException.class, () ->
                user.removeRole(User.RoleName.PROVIDER, now));
    }
}
