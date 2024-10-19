package com.leesh.inflpick.user.port.out;

import com.leesh.inflpick.user.v2.core.entity.vo.Oauth2Provider;

import java.text.MessageFormat;

public class NotSupportedOauth2TypeException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("OAuth2.0 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", Oauth2Provider.availableOauth2Types())));

    public NotSupportedOauth2TypeException(String input) {
        super(ERROR_MESSAGE_FORMAT.toPattern() + input);
    }
}
