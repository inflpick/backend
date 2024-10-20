package com.leesh.inflpick.v2.adapter.out.token.jwt;

import com.leesh.inflpick.v2.domain.token.vo.Token;

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