package com.rentatools.infrastructure.common.security.userdetails;

import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.RoleJpaEntity;
import com.rentatools.infrastructure.identity.adapters.out.persistence.entity.UserJpaEntity;
import com.rentatools.infrastructure.identity.adapters.out.persistence.repositories.UserJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserJpaRepository userRepository;

    public CustomUserDetailsService(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserJpaEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));

        Set<String> roles = user.getRoles().stream()
                .map(RoleJpaEntity::getName)
                .collect(Collectors.toSet());

        return new CustomUserDetails(user.getId(), user.getEmail(), user.getPasswordHash(), user.isActive(), roles);
    }
}
