package com.leesh.inflpick.user.core.domain;

public enum Role {

    USER, ADMIN
    ;
    public static Role from(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
