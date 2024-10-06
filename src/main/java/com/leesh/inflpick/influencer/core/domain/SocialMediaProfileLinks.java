package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public record SocialMediaProfileLinks(Set<SocialMediaProfileLink> links) {

    public SocialMediaProfileLinks(Set<SocialMediaProfileLink> links) {
        this.links = new HashSet<>(links);
    }

    public static SocialMediaProfileLinks from(Set<SocialMediaProfileLink> links) {
        return new SocialMediaProfileLinks(links);
    }

    public SocialMediaProfileLink getSocialMediaLink(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .filter(link -> link.platform().equals(socialMediaPlatform))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("url not found: " + socialMediaPlatform));
    }

    public Boolean hasSocialMedia(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .anyMatch(link -> link.platform().equals(socialMediaPlatform));
    }

    public Set<SocialMediaProfileLink> getImmutable() {
        return Set.copyOf(links);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SocialMediaProfileLinks) obj;
        return Objects.equals(this.links, that.links);
    }

    @Override
    public String toString() {
        return "SocialMediaLinks[" +
                "links=" + links + ']';
    }


    public int size() {
        return links.size();
    }

    public boolean isEmpty() {
        return links.isEmpty();
    }

    @Override
    public Set<SocialMediaProfileLink> links() {
        return Set.copyOf(links);
    }
}
