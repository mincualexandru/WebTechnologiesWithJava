package com.rest.workshop.util;

public class ErrorDetails {
    private String message;
    private int code;

    public ErrorDetails(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {

        return message;
    }

    public int getCode() {

        return code;
    }
}
