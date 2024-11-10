package com.leesh.inflpick.v2.influencer.domain.exception;

public class NotSupportedSnsPlatformException extends RuntimeException {
    public NotSupportedSnsPlatformException(String platform) {
        super("not supported sns platform: " + platform);
    }
}
