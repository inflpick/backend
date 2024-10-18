package com.leesh.inflpick.user.core.domain;

import java.util.Objects;

public class AuthenticationCode {

    private final String value;

    private AuthenticationCode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationCode that = (AuthenticationCode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    public static AuthenticationCode create(String value) {
        return new AuthenticationCode(value);
    }

    public String value() {
        return value;
    }
}
