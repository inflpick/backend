package com.leesh.inflpick.v2.influencer.domain.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class SnsProfileLink {

    private final SnsPlatform platform;
    private final String url;

    private SnsProfileLink(SnsPlatform platform, String url) {
        this.platform = platform;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SnsProfileLink that = (SnsProfileLink) o;
        return platform == that.platform && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(platform, url);
    }

    /* Business Logic */
    public static SnsProfileLink create(SnsPlatform platform, String link) {
        return new SnsProfileLink(platform, link);
    }
}
