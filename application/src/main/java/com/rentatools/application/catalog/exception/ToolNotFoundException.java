package com.rentatools.application.catalog.exception;

import com.rentatools.application.common.exception.ApplicationException;

import java.util.UUID;

public class ToolNotFoundException extends ApplicationException {

    public ToolNotFoundException(UUID id) {
        super("No existe una herramienta con id " + id);
    }
}
