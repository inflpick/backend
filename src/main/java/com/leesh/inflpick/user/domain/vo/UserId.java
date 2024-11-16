package com.leesh.inflpick.user.domain.vo;

public record UserId(String id) {

    /* Business Logic */
    public static UserId create(String value) {
        return new UserId(value);
    }

    public static UserId empty() {
        return new UserId("");
    }

    public boolean isEmpty() {
        return id.isEmpty();
    }
}
