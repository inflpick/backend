package com.leesh.inflpick.v2.domain.user;

import com.leesh.inflpick.v2.domain.user.vo.AuthenticationCode;
import com.leesh.inflpick.v2.domain.user.vo.AuthenticationStatus;
import lombok.Getter;

import java.util.Objects;

@Getter
public class AuthenticationProcess {

    private AuthenticationStatus status;
    private AuthenticationCode code;
    private static final AuthenticationProcess NOT_STARTED = new AuthenticationProcess(
            AuthenticationStatus.NOT_STARTED,
            AuthenticationCode.empty());

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

    void start(AuthenticationCode code) {
        this.status = AuthenticationStatus.IN_PROGRESS;
        this.code = code;
    }

    void complete() {
        this.status = AuthenticationStatus.COMPLETED;
        this.code = AuthenticationCode.empty();
    }

    static AuthenticationProcess notStarted() {
        return new AuthenticationProcess(NOT_STARTED.status, NOT_STARTED.code);
    }

    static AuthenticationProcess create(AuthenticationStatus status, AuthenticationCode code) {
        return new AuthenticationProcess(status, code);
    }

    Boolean canStart() {
        return status.notStarted() || status.completed() || status.inProgress();
    }

    boolean isProgress() {
        return status.inProgress();
    }

}
