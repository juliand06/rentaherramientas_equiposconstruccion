package com.rentatools.domain.identity.model.aggregate;

import com.rentatools.domain.common.AggregateRoot;
import com.rentatools.domain.common.DomainException;
import com.rentatools.domain.common.valueobject.Email;
import com.rentatools.domain.identity.model.valueobject.UserId;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Agregado User (Identidad y Accesos).
 * Refleja fielmente la tabla {@code users} de PostgreSQL (firstName, lastName, email, active).
 */
public class User extends AggregateRoot<UserId> {

    public enum RoleName {
        ADMIN,
        PROVIDER,
        CLIENT
    }

    private Email email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private boolean active;
    private final Set<RoleName> roles = new HashSet<>();
    private final Instant createdAt;
    private Instant updatedAt;

    private User(
            UserId id,
            Email email,
            String passwordHash,
            String firstName,
            String lastName,
            boolean active,
            Set<RoleName> roles,
            Instant createdAt,
            Instant updatedAt
    ) {
        super(id);
        this.email = Objects.requireNonNull(email, "El correo electrónico no puede ser nulo");
        this.passwordHash = validateNonBlank(passwordHash, "El hash de contraseña");
        this.firstName = validateNonBlank(firstName, "El nombre");
        this.lastName = validateNonBlank(lastName, "El apellido");
        this.active = active;
        if (roles == null || roles.isEmpty()) {
            throw new InvalidUserDataException("El usuario debe tener asignado al menos un rol");
        }
        this.roles.addAll(roles);
        this.createdAt = Objects.requireNonNull(createdAt, "La fecha de creación no puede ser nula");
        this.updatedAt = Objects.requireNonNull(updatedAt, "La fecha de actualización no puede ser nula");
    }

    public static User register(
            Email email,
            String passwordHash,
            String firstName,
            String lastName,
            RoleName primaryRole,
            Instant now
    ) {
        UserId userId = UserId.generate();
        Set<RoleName> initialRoles = Set.of(Objects.requireNonNull(primaryRole, "El rol inicial no puede ser nulo"));
        return new User(userId, email, passwordHash, firstName, lastName, true, initialRoles, now, now);
    }

    public static User restore(
            UserId id,
            Email email,
            String passwordHash,
            String firstName,
            String lastName,
            boolean active,
            Set<RoleName> roles,
            Instant createdAt,
            Instant updatedAt
    ) {
        return new User(id, email, passwordHash, firstName, lastName, active, roles, createdAt, updatedAt);
    }

    public void addRole(RoleName role, Instant now) {
        Objects.requireNonNull(role, "El rol no puede ser nulo");
        this.roles.add(role);
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void removeRole(RoleName role, Instant now) {
        Objects.requireNonNull(role, "El rol no puede ser nulo");
        if (this.roles.size() <= 1 && this.roles.contains(role)) {
            throw new InvalidUserDataException("No se puede retirar el único rol del usuario");
        }
        this.roles.remove(role);
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void deactivate(Instant now) {
        this.active = false;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void activate(Instant now) {
        this.active = true;
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void updateProfile(String firstName, String lastName, Instant now) {
        this.firstName = validateNonBlank(firstName, "El nombre");
        this.lastName = validateNonBlank(lastName, "El apellido");
        this.updatedAt = Objects.requireNonNull(now);
    }

    public void changePassword(String newPasswordHash, Instant now) {
        this.passwordHash = validateNonBlank(newPasswordHash, "La nueva contraseña");
        this.updatedAt = Objects.requireNonNull(now);
    }

    public Email getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isActive() {
        return active;
    }

    public Set<RoleName> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    private static String validateNonBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " no puede ser nulo");
        String trimmed = value.trim();
        if (trimmed.isBlank()) {
            throw new InvalidUserDataException(fieldName + " no puede estar vacío");
        }
        return trimmed;
    }

    public static class InvalidUserDataException extends DomainException {
        public InvalidUserDataException(String message) {
            super(message);
        }
    }
}
