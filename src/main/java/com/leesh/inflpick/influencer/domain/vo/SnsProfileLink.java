package com.leesh.inflpick.influencer.domain.vo;

public record SnsProfileLink(SnsPlatform platform, String url) {

    /* Business Logic */
    public static SnsProfileLink create(SnsPlatform platform, String link) {
        return new SnsProfileLink(platform, link);
    }
}
