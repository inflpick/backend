package com.leesh.inflpick.user.port.in;

import java.text.MessageFormat;
import java.time.Instant;

public class AlreadyConnectedOauth2User extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("이미 연결된 소셜 계정 사용자가 있습니다. 연결일: {0}");

    public AlreadyConnectedOauth2User(Instant connectedDate) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{connectedDate}));
    }
}
