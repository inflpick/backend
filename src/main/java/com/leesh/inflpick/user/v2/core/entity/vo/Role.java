package com.leesh.inflpick.user.v2.core.entity.vo;

public enum Role {

    USER, ADMIN
    ;
    public static Role from(String role) {
        return Role.valueOf(role.toUpperCase());
    }

}
