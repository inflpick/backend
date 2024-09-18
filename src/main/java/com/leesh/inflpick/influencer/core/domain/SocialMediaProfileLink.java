package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

public record SocialMediaProfileLink(@NotNull SocialMediaPlatform platform,
                                     @NotNull URI profileLink) {

    public final static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 URI 형식으로 입력해주세요. 입력 값: {0}");

    public static @NotNull SocialMediaProfileLink of(@NotNull SocialMediaPlatform platform,
                                                     @NotNull String uri) {
        try {
            return new SocialMediaProfileLink(platform, new URI(uri));
        } catch (URISyntaxException e) {
            throw new SocialMediaProfileLinkUriSyntaxException(ERROR_MESSAGE_FORMAT.format(new Object[]{uri}));
        }
    }

    public String getPlatformName() {
        return platform.name();
    }

    public String getProfileUri() {
        return profileLink.toString();
    }
}
