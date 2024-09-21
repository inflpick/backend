package com.leesh.inflpick.influencer.core.domain.exception;

import java.text.MessageFormat;

public class SocialMediaProfileLinkUriSyntaxException extends IllegalArgumentException {

    public final static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("올바른 URI 형식으로 입력해주세요. 현재 입력값: {0}");

    public SocialMediaProfileLinkUriSyntaxException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
