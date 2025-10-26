package com.scaleupindia.exceptions;

import java.io.Serial;

public class DuplicateOwnerException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateOwnerException(String message) {
        super(message);
    }
}
