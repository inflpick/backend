package com.leesh.inflpick.user.adapter.out.jwt;

import com.leesh.inflpick.user.port.out.Token;

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
