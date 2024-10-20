package com.leesh.inflpick.v2.adapter.out.token.jwt.auth;

import com.leesh.inflpick.v2.domain.auth.vo.Token;

public record Jwt(String value, Integer expiresInSeconds) implements Token {

    @Override
    public String value() {
        return value;
    }

    @Override
    public Integer expiresInSeconds() {
        return expiresInSeconds;
    }
}