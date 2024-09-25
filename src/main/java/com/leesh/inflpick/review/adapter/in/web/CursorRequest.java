package com.leesh.inflpick.review.adapter.in.web;

import java.util.Objects;

public record CursorRequest(Integer cursor,
                            Integer limit,
                            String influencerId,
                            String productId) {

    public CursorRequest(Integer cursor,
                         Integer limit,
                         String influencerId,
                         String productId) {
        this.cursor = Objects.requireNonNullElse(cursor, 0);
        this.limit = Objects.requireNonNullElse(limit, 20);
        this.influencerId = Objects.requireNonNullElse(influencerId, "");
        this.productId = Objects.requireNonNullElse(productId, "");
    }
}
