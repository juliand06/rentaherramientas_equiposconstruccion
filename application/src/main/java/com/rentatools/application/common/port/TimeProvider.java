package com.rentatools.application.common.port;

import java.time.Instant;

/**
 * Puerto para obtener la hora actual. Permite que los casos de uso sean deterministas en las
 * pruebas (reloj fijo) y evita llamar a {@code Instant.now()} dentro del dominio.
 */
public interface TimeProvider {

    Instant now();
}
