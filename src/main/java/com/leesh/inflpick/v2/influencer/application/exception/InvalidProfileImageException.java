package com.leesh.inflpick.v2.influencer.application.exception;

public class InvalidProfileImageException extends RuntimeException {
    public InvalidProfileImageException(String message) {
        super("Profile image format is not supported, file: %s".formatted(message));
    }
}
