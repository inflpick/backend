package com.leesh.inflpick.user.core.domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class AuthenticationProcess {

    private final AuthenticationStatus status;
    private final AuthenticationCode code;

    private AuthenticationProcess(AuthenticationStatus status, AuthenticationCode code) {
        this.status = status;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationProcess that = (AuthenticationProcess) o;
        return status == that.status && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code);
    }

    public static AuthenticationProcess start(AuthenticationCode code) {
        return new AuthenticationProcess(AuthenticationStatus.IN_PROGRESS, code);
    }

    public static AuthenticationProcess create(AuthenticationStatus authenticationStatus, AuthenticationCode authenticationCode) {
        return new AuthenticationProcess(authenticationStatus, authenticationCode);
    }

}
