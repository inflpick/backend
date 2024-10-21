package com.leesh.inflpick.v2.token.domain.vo;

public enum GrantType {

    AUTHENTICATION_CODE,
    REFRESH_TOKEN
    ;

    public String toLowerCaseName() {
        return this.name().toLowerCase();
    }
}
