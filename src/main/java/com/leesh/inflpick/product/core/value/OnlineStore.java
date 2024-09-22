package com.leesh.inflpick.product.core.value;

import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.product.core.exception.InvalidOnlineStoreException;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

public enum OnlineStore {

    COUPANG(URI.create("https://www.coupang.com")),
    ;

    private final URI uri;

    OnlineStore(URI uri) {
        this.uri = uri;
    }

    public static List<String> availablePlatforms() {
        return Arrays.stream(SocialMediaPlatform.values())
                .map(SocialMediaPlatform::name)
                .toList();
    }

    public static void validate(String platform) {
        if (Arrays.stream(SocialMediaPlatform.values())
                .noneMatch(socialMediaPlatform -> socialMediaPlatform.name().equals(platform))) {
            throw new InvalidOnlineStoreException(platform);
        }
    }

    public static OnlineStore from(String platform) {
        validate(platform);
        return OnlineStore.valueOf(platform);
    }
}
