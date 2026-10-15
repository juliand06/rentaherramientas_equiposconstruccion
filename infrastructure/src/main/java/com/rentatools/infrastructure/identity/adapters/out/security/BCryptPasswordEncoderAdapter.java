package com.rentatools.infrastructure.identity.adapters.out.security;

import com.rentatools.application.identity.port.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final PasswordEncoder delegate = new BCryptPasswordEncoder(12);

    @Override
    public String encode(CharSequence rawPassword) {
        return delegate.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return delegate.matches(rawPassword, encodedPassword);
    }
}
