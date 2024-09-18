package com.leesh.inflpick.influencer.core.domain;

import java.text.MessageFormat;

public class ProfileImageUriSyntaxException extends RuntimeException {

    public final static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 URI 형식으로 입력해주세요. 현재 입력값: {0}");

    public ProfileImageUriSyntaxException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
