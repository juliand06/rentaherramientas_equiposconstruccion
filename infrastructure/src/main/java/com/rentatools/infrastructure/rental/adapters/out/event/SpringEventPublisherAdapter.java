package com.rentatools.infrastructure.rental.adapters.out.event;

import com.rentatools.application.common.event.DomainEventPublisher;
import com.rentatools.domain.common.DomainEvent;
import org.springframework.context.ApplicationEventPublisher;

public class SpringEventPublisherAdapter implements DomainEventPublisher {

    private final ApplicationEventPublisher publisher;

    public SpringEventPublisherAdapter(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }
}
