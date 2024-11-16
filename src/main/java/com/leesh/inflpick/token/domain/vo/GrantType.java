package com.leesh.inflpick.token.domain.vo;

public enum GrantType {

    AUTHENTICATION_CODE,
    REFRESH_TOKEN
    ;

    public String toLowerCaseName() {
        return this.name().toLowerCase();
    }
}
