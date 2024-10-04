package com.leesh.inflpick.influencer.core.domain.exception;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;

import java.text.MessageFormat;

public class InvalidSocialMediaPlatformException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("소셜 미디어 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", SocialMediaPlatform.availablePlatforms())));

    public InvalidSocialMediaPlatformException(String input) {
        super(ERROR_MESSAGE_FORMAT.toPattern() + input);
    }
}
