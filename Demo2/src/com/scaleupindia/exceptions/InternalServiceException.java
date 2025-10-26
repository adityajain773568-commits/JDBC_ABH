package com.scaleupindia.exceptions;

public class InternalServiceException extends RuntimeException {
    public InternalServiceException(String message) {
        super(message);
    }
}
