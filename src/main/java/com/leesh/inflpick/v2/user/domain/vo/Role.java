package com.leesh.inflpick.v2.user.domain.vo;

public enum Role {

    USER, ADMIN
    ;
    public static Role from(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
