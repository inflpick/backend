package com.leesh.inflpick.v2.token.adapter.out.token.jwt;

import com.leesh.inflpick.v2.token.domain.Token;

public record Jwt(String value, Integer expiresInSeconds) implements Token {

    public static Jwt create(String value, Integer expiresInSeconds) {
        return new Jwt(value, expiresInSeconds);
    }

    public static Jwt create(String value) {
        return new Jwt(value, 0);
    }

}