package com.leesh.inflpick.v2.review.domain.vo;

public record ReviewId(String id) {

    /* Business Logic */
    public static ReviewId create(String id) {
        return new ReviewId(id);
    }

    public static ReviewId empty() {
        return new ReviewId(null);
    }

    public boolean isEmpty() {
        return id == null || id.isBlank();
    }
}
