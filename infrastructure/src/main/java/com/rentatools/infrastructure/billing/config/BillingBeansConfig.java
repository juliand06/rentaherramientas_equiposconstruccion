package com.rentatools.infrastructure.billing.config;

import com.rentatools.application.billing.usecase.GetPaymentUseCase;
import com.rentatools.application.billing.usecase.ProcessPaymentUseCase;
import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.domain.billing.port.repository.PaymentRepository;
import com.rentatools.infrastructure.billing.adapters.out.persistence.PaymentRepositoryAdapter;
import com.rentatools.infrastructure.billing.adapters.out.persistence.repositories.PaymentJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BillingBeansConfig {

    @Bean
    public PaymentRepository paymentRepository(PaymentJpaRepository jpaRepository) {
        return new PaymentRepositoryAdapter(jpaRepository);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentRepository repository, TimeProvider timeProvider) {
        return new ProcessPaymentUseCase(repository, timeProvider);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentRepository repository) {
        return new GetPaymentUseCase(repository);
    }
}
