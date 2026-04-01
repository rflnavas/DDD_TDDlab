package com.rnr.error;

public class InvalidValue extends RuntimeException {

    public InvalidValue(String message) {
        super(message);
    }
}
