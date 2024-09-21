package com.leesh.inflpick.influencer.core.domain.value;

import lombok.Getter;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Getter
public enum SocialMediaPlatform {

    INSTAGRAM(URI.create("https://www.instagram.com")),
    YOUTUBE(URI.create("https://www.youtube.com")),
    TIKTOK(URI.create("https://www.tiktok.com")),
    FACEBOOK(URI.create("https://www.facebook.com")),
    X(URI.create("https://www.x.com")),
    DISCORD(URI.create("https://www.discord.com")),
    BLOG(URI.create("")),
    OTHER(URI.create(""))
    ;

    private final URI uri;

    SocialMediaPlatform(URI uri) {
        this.uri = uri;
    }

    public static List<String> availablePlatforms() {
        return Arrays.stream(SocialMediaPlatform.values())
                .map(SocialMediaPlatform::name)
                .toList();
    }
}
