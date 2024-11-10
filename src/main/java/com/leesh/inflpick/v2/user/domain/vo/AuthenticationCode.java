package com.leesh.inflpick.v2.user.domain.vo;

public record AuthenticationCode(String code) {

    public static AuthenticationCode create(String value) {
        return new AuthenticationCode(value);
    }

    public static AuthenticationCode empty() {
        return new AuthenticationCode("");
    }
}
