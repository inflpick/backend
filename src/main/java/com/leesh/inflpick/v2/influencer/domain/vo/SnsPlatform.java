package com.leesh.inflpick.v2.influencer.domain.vo;

import com.leesh.inflpick.v2.influencer.domain.exception.NotSupportSnsPlatformException;

import java.util.Arrays;
import java.util.List;

public enum SnsPlatform {
    INSTAGRAM,
    YOUTUBE,
    TIKTOK,
    FACEBOOK,
    TWITTER,
    BLOG,
    CAFE,
    ETC
    ;

    public static List<String> availableValues() {
        return Arrays.stream(SnsPlatform.values())
                .map(SnsPlatform::name)
                .toList();
    }

    public static SnsPlatform from(String platform) {
        try {
            return SnsPlatform.valueOf(platform);
        } catch (IllegalArgumentException e) {
            // SnsPlatform must be one of the
            throw new NotSupportSnsPlatformException("Invalid SNS platform: " + platform);
        }
    }
}
