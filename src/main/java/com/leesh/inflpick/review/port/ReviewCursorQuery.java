package com.leesh.inflpick.review.port;

import java.time.Instant;

public record ReviewCursorQuery(
        String influencerId,
        String productId,
        Instant cursor,
        Integer limit) {

    public ReviewCursorQuery {
        if (limit < 1) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
    }

}
