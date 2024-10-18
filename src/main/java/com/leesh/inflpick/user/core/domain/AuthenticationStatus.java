package com.leesh.inflpick.user.core.domain;

public enum AuthenticationStatus {

    IN_PROGRESS,
    AUTHENTICATED,
    UNAUTHENTICATED;

    public boolean isAuthenticated() {
        return this == AUTHENTICATED;
    }

    public boolean isUnauthenticated() {
        return this == UNAUTHENTICATED;
    }
}
