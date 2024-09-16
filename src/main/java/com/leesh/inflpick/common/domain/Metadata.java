package com.leesh.inflpick.common.domain;

import java.time.Instant;
import java.util.Objects;

public record Metadata(String createdBy,
                       Instant createdAt,
                       String lastModifiedBy,
                       Instant lastModifiedAt) {

    public Metadata {
        Objects.requireNonNull(createdBy, "createdBy must not be null");
        Objects.requireNonNull(createdAt, "createdAt must not be null");
        Objects.requireNonNull(lastModifiedBy, "lastModifiedBy must not be null");
        Objects.requireNonNull(lastModifiedAt, "lastModifiedAt must not be null");
    }

}
