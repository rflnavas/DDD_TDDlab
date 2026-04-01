package com.rnr.error;

public class InvalidRange extends RuntimeException {

    public InvalidRange(String message) {
        super(message);
    }
}
