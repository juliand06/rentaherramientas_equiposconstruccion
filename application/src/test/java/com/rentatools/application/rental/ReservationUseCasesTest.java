package com.rentatools.application.rental;

import com.rentatools.application.common.event.DomainEventPublisher;
import com.rentatools.application.rental.command.CreateReservationCommand;
import com.rentatools.application.rental.dto.ReservationDto;
import com.rentatools.application.rental.usecase.ConfirmReservationUseCase;
import com.rentatools.application.rental.usecase.CreateReservationUseCase;
import com.rentatools.application.rental.usecase.GetReservationUseCase;
import com.rentatools.domain.common.DomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationUseCasesTest {

    private static final Instant NOW = Instant.parse("2026-10-15T12:00:00Z");

    private InMemoryReservationRepository repository;
    private List<DomainEvent> publishedEvents;
    private CreateReservationUseCase createUseCase;
    private ConfirmReservationUseCase confirmUseCase;
    private GetReservationUseCase getUseCase;

    @BeforeEach
    void setUp() {
        repository = new InMemoryReservationRepository();
        publishedEvents = new ArrayList<>();
        DomainEventPublisher publisher = publishedEvents::add;

        createUseCase = new CreateReservationUseCase(repository, () -> NOW);
        confirmUseCase = new ConfirmReservationUseCase(repository, publisher, () -> NOW);
        getUseCase = new GetReservationUseCase(repository);
    }

    @Test
    @DisplayName("Crea una reserva calculando el costo total correctamente")
    void shouldCreateReservation() {
        CreateReservationCommand cmd = new CreateReservationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 5), // 5 días
                new BigDecimal("60000.00"),
                new BigDecimal("200000.00")
        );

        ReservationDto dto = createUseCase.execute(cmd);

        assertNotNull(dto.id());
        assertEquals("PENDING", dto.status());
        assertEquals(5, dto.totalDays());
        assertEquals(new BigDecimal("300000.00"), dto.totalRentalAmount());
    }

    @Test
    @DisplayName("Confirmar reserva cambia a CONFIRMED y emite evento de dominio")
    void shouldConfirmReservationAndPublishEvent() {
        CreateReservationCommand cmd = new CreateReservationCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDate.of(2026, 11, 1),
                LocalDate.of(2026, 11, 3),
                new BigDecimal("50000.00"),
                new BigDecimal("100000.00")
        );
        ReservationDto created = createUseCase.execute(cmd);

        ReservationDto confirmed = confirmUseCase.execute(created.id());

        assertEquals("CONFIRMED", confirmed.status());
        assertEquals(1, publishedEvents.size());
    }
}
