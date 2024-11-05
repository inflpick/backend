package com.leesh.inflpick.v2.common.application.exception;

public class FileFormatException extends Throwable {

    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileFormatException(String message) {
        super(message);
    }
}
