package com.leesh.inflpick.product.core.domain.value;

import com.leesh.inflpick.product.core.domain.exception.InvalidOnlineStoreException;

import java.util.Arrays;
import java.util.List;

public enum OnlineStore {

    COUPANG,
    ;

    public static List<String> availablePlatforms() {
        return Arrays.stream(OnlineStore.values())
                .map(OnlineStore::name)
                .toList();
    }

    public static void validate(String platform) {
        if (Arrays.stream(OnlineStore.values())
                .noneMatch(onlineStore -> onlineStore.name().equals(platform))) {
            throw new InvalidOnlineStoreException(platform);
        }
    }

    public static OnlineStore from(String platform) {
        validate(platform);
        return OnlineStore.valueOf(platform);
    }
}
