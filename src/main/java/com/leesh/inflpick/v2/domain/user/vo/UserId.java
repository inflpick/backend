package com.leesh.inflpick.v2.domain.user.vo;

import java.util.Objects;

public class UserId {

    private final String value;

    private UserId() {
        this.value = "";
    }

    private UserId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId = (UserId) o;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /* Business Logic */
    public static UserId create(String value) {
        return new UserId(value);
    }

    public static UserId empty() {
        return new UserId();
    }

    public String value() {
        return value;
    }

    public boolean isEmpty() {
        return value.isEmpty();
    }
}
