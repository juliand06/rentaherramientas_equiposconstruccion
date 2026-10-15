package com.rentatools.application.catalog.exception;

import com.rentatools.application.common.exception.ApplicationException;

public class DuplicateToolCodeException extends ApplicationException {

    public DuplicateToolCodeException(String code) {
        super("Ya existe una herramienta con el código " + code);
    }
}
