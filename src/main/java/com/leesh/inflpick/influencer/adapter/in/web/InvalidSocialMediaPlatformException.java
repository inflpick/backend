package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;

import java.text.MessageFormat;

public class InvalidSocialMediaPlatformException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("소셜 미디어 타입은 %s 중 하나여야 합니다. 현재 입력값: {0}".formatted(String.join(", ", SocialMediaPlatform.availablePlatforms())));

    public InvalidSocialMediaPlatformException(String message) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{message}));
    }
}
