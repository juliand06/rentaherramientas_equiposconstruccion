package com.rentatools.application.identity.command;

import com.rentatools.domain.identity.model.aggregate.User.RoleName;

/** Comando para registrar un usuario nuevo. */
public record RegisterUserCommand(
        String email,
        String rawPassword,
        String firstName,
        String lastName,
        RoleName role
) {
}
