package com.rest.workshop.util;

public class CustomizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomizedException(String message) {
        super(message);
    }
}
