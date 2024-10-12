package com.leesh.inflpick.user.port.out;

public interface AuthenticationToken {

    String value();

    Integer expiresInSeconds();
}
