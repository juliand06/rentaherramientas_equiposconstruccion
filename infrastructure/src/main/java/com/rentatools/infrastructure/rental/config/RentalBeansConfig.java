package com.rentatools.infrastructure.rental.config;

import com.rentatools.application.common.event.DomainEventPublisher;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.application.rental.usecase.ConfirmReservationUseCase;
import com.rentatools.application.rental.usecase.CreateReservationUseCase;
import com.rentatools.application.rental.usecase.GetReservationUseCase;
import com.rentatools.domain.rental.port.repository.ReservationRepository;
import com.rentatools.infrastructure.rental.adapters.out.event.SpringEventPublisherAdapter;
import com.rentatools.infrastructure.rental.adapters.out.persistence.ReservationRepositoryAdapter;
import com.rentatools.infrastructure.rental.adapters.out.persistence.repositories.ReservationJpaRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentalBeansConfig {

    @Bean
    public DomainEventPublisher domainEventPublisher(ApplicationEventPublisher publisher) {
        return new SpringEventPublisherAdapter(publisher);
    }

    @Bean
    public ReservationRepository reservationRepository(ReservationJpaRepository jpaRepository) {
        return new ReservationRepositoryAdapter(jpaRepository);
    }

    @Bean
    public CreateReservationUseCase createReservationUseCase(ReservationRepository repository, TimeProvider timeProvider) {
        return new CreateReservationUseCase(repository, timeProvider);
    }

    @Bean
    public ConfirmReservationUseCase confirmReservationUseCase(ReservationRepository repository,
                                                               DomainEventPublisher publisher,
                                                               TimeProvider timeProvider) {
        return new ConfirmReservationUseCase(repository, publisher, timeProvider);
    }

    @Bean
    public GetReservationUseCase getReservationUseCase(ReservationRepository repository) {
        return new GetReservationUseCase(repository);
    }
}
