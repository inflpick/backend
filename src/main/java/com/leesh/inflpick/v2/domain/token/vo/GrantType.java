package com.leesh.inflpick.v2.domain.token.vo;

public enum GrantType {

    AUTHENTICATION_CODE,
    REFRESH_TOKEN
    ;

    public String toLowerCaseName() {
        return this.name().toLowerCase();
    }
}
