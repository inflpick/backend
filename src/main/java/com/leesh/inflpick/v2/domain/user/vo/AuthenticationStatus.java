package com.leesh.inflpick.v2.domain.user.vo;

public enum AuthenticationStatus {

    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED
    ;

    public boolean inProgress() {
        return this == IN_PROGRESS;
    }

    public boolean completed() {
        return this == COMPLETED;
    }

    public boolean notStarted() {
        return this == NOT_STARTED;
    }
}
