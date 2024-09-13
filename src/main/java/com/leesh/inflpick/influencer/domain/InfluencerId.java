package com.leesh.inflpick.influencer.domain;

import com.leesh.inflpick.common.port.out.UuidHolder;
import lombok.NonNull;

public record InfluencerId(@NonNull String uuid) {

    public InfluencerId(@NonNull UuidHolder uuidHolder) {
        this(uuidHolder.uuid());
        if (this.uuid.isBlank()) {
            throw new IllegalArgumentException("uuid must not be blank");
        }
    }
}
