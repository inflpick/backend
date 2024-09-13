package com.leesh.inflpick.influencer.domain;

import lombok.Getter;

import java.net.URI;

@Getter
public enum SocialMediaPlatform {

    INSTAGRAM(URI.create("https://www.instagram.com")),
    YOUTUBE(URI.create("https://www.youtube.com")),
    TIKTOK(URI.create("https://www.tiktok.com")),
    FACEBOOK(URI.create("https://www.facebook.com")),
    TWITTER(URI.create("https://www.twitter.com")),
    ;

    private final URI uri;

    SocialMediaPlatform(URI uri) {
        this.uri = uri;
    }
}
