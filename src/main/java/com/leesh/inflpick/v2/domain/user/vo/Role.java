package com.leesh.inflpick.v2.domain.user.vo;

public enum Role {

    USER, ADMIN
    ;
    public static Role from(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
