package com.leesh.inflpick.influencer.core.domain;

import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public record SocialMediaProfileLink(@NotNull SocialMediaPlatform platform,
                                     @NotNull URI profileLink) {

    public static @NotNull SocialMediaProfileLink of(@NotNull SocialMediaPlatform platform,
                                                     @NotNull String uri) {
        try {
            return new SocialMediaProfileLink(platform, new URI(uri));
        } catch (URISyntaxException e) {
            throw new SocialMediaProfileLinkUriSyntaxException(uri);
        }
    }

    public String getPlatformName() {
        return platform.name();
    }

    public String getProfileUri() {
        return profileLink.toString();
    }
}
