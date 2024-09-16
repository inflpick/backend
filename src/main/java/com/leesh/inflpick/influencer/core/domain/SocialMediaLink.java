package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

public record SocialMediaLink(@NotNull SocialMediaPlatform platform,
                              @NotNull URI uri) {

    private final static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 URI 형식이 아닙니다.");

    public static @NotNull SocialMediaLink of(@NotNull SocialMediaPlatform platform,
                                     @NotNull String uri) {
        try {
            return new SocialMediaLink(platform, new URI(uri));
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(ERROR_MESSAGE_FORMAT.format(new Object[]{uri}));
        }
    }

}
