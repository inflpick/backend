package com.leesh.inflpick.v2.domain.auth.vo;

public interface Token {

    String value();

    Integer expiresInSeconds();
}
