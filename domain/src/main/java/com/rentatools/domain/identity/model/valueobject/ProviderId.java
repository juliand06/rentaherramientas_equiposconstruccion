package com.rentatools.domain.identity.model.valueobject;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Identificador del perfil de proveedor ({@code provider_profiles.id}).
 * Es distinto de {@link UserId}: el esquema relaciona las herramientas con el perfil, no con el usuario.
 */
public record ProviderId(UUID value) implements Serializable {

    public ProviderId {
        Objects.requireNonNull(value, "El identificador del proveedor no puede ser nulo");
    }

    public static ProviderId generate() {
        return new ProviderId(UUID.randomUUID());
    }

    public static ProviderId of(UUID value) {
        return new ProviderId(value);
    }
}
