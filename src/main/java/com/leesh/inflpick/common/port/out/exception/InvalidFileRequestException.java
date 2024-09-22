package com.leesh.inflpick.common.port.out.exception;

public class InvalidFileRequestException extends RuntimeException {

    public InvalidFileRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileRequestException(String message) {
        super(message);
    }
}
