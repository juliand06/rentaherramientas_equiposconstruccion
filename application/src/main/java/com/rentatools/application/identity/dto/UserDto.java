package com.rentatools.application.identity.dto;

import com.rentatools.domain.identity.model.aggregate.User;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/** DTO seguro de usuario: omite el hash de contraseña. */
public record UserDto(
        UUID id,
        String email,
        String firstName,
        String lastName,
        String fullName,
        boolean active,
        Set<String> roles,
        Instant createdAt,
        Instant updatedAt
) {

    public static UserDto from(User user) {
        return new UserDto(
                user.getId().value(),
                user.getEmail().value(),
                user.getFirstName(),
                user.getLastName(),
                user.getFullName(),
                user.isActive(),
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
