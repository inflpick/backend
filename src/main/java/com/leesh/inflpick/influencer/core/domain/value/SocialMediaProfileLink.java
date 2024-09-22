package com.leesh.inflpick.influencer.core.domain.value;

import org.jetbrains.annotations.NotNull;

public record SocialMediaProfileLink(@NotNull SocialMediaPlatform platform,
                                     @NotNull String uri) {

    public static @NotNull SocialMediaProfileLink of(@NotNull SocialMediaPlatform platform,
                                                     @NotNull String uri) {
        return new SocialMediaProfileLink(platform, uri);
    }

    public String getPlatformName() {
        return platform.name();
    }

    public String getProfileUri() {
        return uri;
    }
}
