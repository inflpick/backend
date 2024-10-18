package com.leesh.inflpick.user.port.out;

public interface Token {

    String value();

    Integer expiresInSeconds();
}
