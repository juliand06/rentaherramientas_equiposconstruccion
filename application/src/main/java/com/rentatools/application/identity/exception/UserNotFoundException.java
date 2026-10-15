package com.rentatools.application.identity.exception;

import com.rentatools.application.common.exception.ApplicationException;

import java.util.UUID;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(UUID id) {
        super("No existe un usuario con id " + id);
    }

    public UserNotFoundException(String email) {
        super("No existe un usuario con correo " + email);
    }
}
