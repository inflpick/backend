package com.leesh.inflpick.v2.adapter.out.token.jwt;

import com.leesh.inflpick.v2.domain.token.vo.Token;

import java.util.Objects;

public final class Jwt implements Token {
    private final String value;
    private final Integer expiresInSeconds;

    private Jwt(String value, Integer expiresInSeconds) {
        this.value = value;
        this.expiresInSeconds = expiresInSeconds;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public Integer expiresInSeconds() {
        return expiresInSeconds;
    }

    public static Jwt create(String value, Integer expiresInSeconds) {
        return new Jwt(value, expiresInSeconds);
    }

    public static Jwt create(String value) {
        return new Jwt(value, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Jwt) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}