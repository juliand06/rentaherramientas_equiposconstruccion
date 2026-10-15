package com.rentatools.application.identity.port;

/**
 * Puerto de salida para hashear contraseñas.
 * Permite que application use BCrypt sin depender de Spring Security.
 */
public interface PasswordEncoderPort {

    String encode(CharSequence rawPassword);

    boolean matches(CharSequence rawPassword, String encodedPassword);
}
