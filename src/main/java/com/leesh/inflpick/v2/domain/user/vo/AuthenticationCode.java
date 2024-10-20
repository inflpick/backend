package com.leesh.inflpick.v2.domain.user.vo;

import java.util.Objects;

public final class AuthenticationCode {

    private final String value;

    private AuthenticationCode(String value) {
        this.value = value;
    }
    private AuthenticationCode() {
        this.value = "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AuthenticationCode) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public String value() {
        return value;
    }

    public static AuthenticationCode create(String value) {
        return new AuthenticationCode(value);
    }

    public static AuthenticationCode empty() {
        return new AuthenticationCode();
    }
}
