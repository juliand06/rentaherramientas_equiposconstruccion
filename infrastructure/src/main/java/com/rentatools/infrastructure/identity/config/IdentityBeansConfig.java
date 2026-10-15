package com.rentatools.infrastructure.identity.config;

import com.rentatools.application.common.port.TimeProvider;
import com.rentatools.application.identity.port.PasswordEncoderPort;
import com.rentatools.application.identity.usecase.GetUserProfileUseCase;
import com.rentatools.application.identity.usecase.RegisterUserUseCase;
import com.rentatools.domain.identity.port.repository.UserRepository;
import com.rentatools.infrastructure.identity.adapters.out.persistence.UserRepositoryAdapter;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.RoleJpaRepository;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.UserJpaRepository;
import com.rentatools.infrastructure.identity.adapters.out.security.BCryptPasswordEncoderAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityBeansConfig {

    @Bean
    public PasswordEncoderPort passwordEncoderPort() {
        return new BCryptPasswordEncoderAdapter();
    }

    @Bean
    public UserRepository userRepository(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository) {
        return new UserRepositoryAdapter(userJpaRepository, roleJpaRepository);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepository userRepository,
                                                   PasswordEncoderPort passwordEncoderPort,
                                                   TimeProvider timeProvider) {
        return new RegisterUserUseCase(userRepository, passwordEncoderPort, timeProvider);
    }

    @Bean
    public GetUserProfileUseCase getUserProfileUseCase(UserRepository userRepository) {
        return new GetUserProfileUseCase(userRepository);
    }
}
