package com.leesh.inflpick.common.adapter.out.storage;

public class InvalidFileRequestException extends RuntimeException {

    public InvalidFileRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileRequestException(String message) {
        super(message);
    }
}
