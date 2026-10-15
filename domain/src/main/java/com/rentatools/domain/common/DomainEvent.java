package com.rentatools.domain.common;

import java.io.Serializable;
import java.time.Instant;

/**
 * Contrato base para todos los eventos del dominio.
 * Un evento de dominio representa un hecho ocurrido en el pasado dentro del negocio.
 * Es inmutable y no tiene dependencias de infraestructura ni frameworks.
 */
public interface DomainEvent extends Serializable {
    Instant occurredOn();
}
