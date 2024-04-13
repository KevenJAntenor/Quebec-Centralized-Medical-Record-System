package com.santeConnect.domain.exception;

public class InvalidCoordinateException extends RuntimeException {

    public InvalidCoordinateException() {
        super("Invalid coordinate provided.");
    }
}
