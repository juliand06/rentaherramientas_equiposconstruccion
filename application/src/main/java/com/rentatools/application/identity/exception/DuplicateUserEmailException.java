package com.rentatools.application.identity.exception;

import com.rentatools.application.common.exception.ApplicationException;

public class DuplicateUserEmailException extends ApplicationException {

    public DuplicateUserEmailException(String email) {
        super("Ya existe un usuario registrado con el correo " + email);
    }
}
