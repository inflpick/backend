package com.leesh.inflpick.influencer.domain;

import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

public record SocialMediaLinks(@Nonnull List<SocialMediaLink> links) {

    public SocialMediaLinks {
        Objects.requireNonNull(links, "links must not be null");
    }

    public SocialMediaLink getSocialMediaLink(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .filter(link -> link.platform().equals(socialMediaPlatform))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("uri not found: " + socialMediaPlatform));
    }

    public List<SocialMediaLink> getLinks() {
        return List.copyOf(links);
    }

    public Boolean hasSocialMedia(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .anyMatch(link -> link.platform().equals(socialMediaPlatform));
    }

}
