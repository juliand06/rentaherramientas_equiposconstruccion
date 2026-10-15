package com.rentatools.domain.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Raíz de Agregado base en Domain-Driven Design (DDD).
 * Encapsula la consistencia transaccional y el registro de eventos de dominio generados
 * durante las operaciones de negocio.
 *
 * @param <ID> Tipo del identificador del agregado
 */
public abstract class AggregateRoot<ID> {

    protected final ID id;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected AggregateRoot(ID id) {
        this.id = Objects.requireNonNull(id, "El identificador del agregado no puede ser nulo");
    }

    public ID getId() {
        return id;
    }

    protected void registerEvent(DomainEvent event) {
        Objects.requireNonNull(event, "El evento de dominio no puede ser nulo");
        this.domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(this.domainEvents);
        this.domainEvents.clear();
        return Collections.unmodifiableList(events);
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(this.domainEvents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregateRoot<?> that = (AggregateRoot<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
