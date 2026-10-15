package com.rentatools.application.common.event;

import com.rentatools.domain.common.DomainEvent;

/**
 * Puerto para la publicación de eventos de dominio generados por los agregados.
 */
public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
