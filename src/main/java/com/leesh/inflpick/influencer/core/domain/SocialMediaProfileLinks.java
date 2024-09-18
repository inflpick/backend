package com.leesh.inflpick.influencer.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SocialMediaProfileLinks {

    private final List<SocialMediaProfileLink> links;

    public SocialMediaProfileLinks(List<SocialMediaProfileLink> links) {
        this.links = new ArrayList<>(links);
    }

    public SocialMediaProfileLink getSocialMediaLink(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .filter(link -> link.platform().equals(socialMediaPlatform))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("link not found: " + socialMediaPlatform));
    }

    public Boolean hasSocialMedia(SocialMediaPlatform socialMediaPlatform) {
        return links.stream()
                .anyMatch(link -> link.platform().equals(socialMediaPlatform));
    }

    public List<SocialMediaProfileLink> getImmutable() {
        return List.copyOf(links);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SocialMediaProfileLinks) obj;
        return Objects.equals(this.links, that.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(links);
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

    public List<SocialMediaProfileLink> getLinks() {
        return List.copyOf(links);
    }
}
