package com.leesh.inflpick.influencer.domain;

import jakarta.annotation.Nonnull;

import java.net.URI;
import java.util.Objects;

public record SocialMediaLink(@Nonnull SocialMediaPlatform platform,
                              @Nonnull URI uri) {

    public SocialMediaLink {
        Objects.requireNonNull(platform, "platform must not be null");
        Objects.requireNonNull(uri, "uri must not be null");
    }

}
