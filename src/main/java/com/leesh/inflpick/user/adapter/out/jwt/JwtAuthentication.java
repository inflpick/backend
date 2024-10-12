package com.leesh.inflpick.user.adapter.out.jwt;

import com.leesh.inflpick.user.port.out.AuthenticationToken;

public record JwtAuthentication(String value, Integer expiresInSeconds) implements AuthenticationToken {

    @Override
    public String value() {
        return value;
    }

    @Override
    public Integer expiresInSeconds() {
        return expiresInSeconds;
    }
}
