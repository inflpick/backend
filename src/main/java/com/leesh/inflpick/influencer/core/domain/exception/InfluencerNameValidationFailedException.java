package com.leesh.inflpick.influencer.core.domain.exception;

import java.text.MessageFormat;

import static com.leesh.inflpick.influencer.core.domain.value.InfluencerName.MAX_LENGTH;
import static com.leesh.inflpick.influencer.core.domain.value.InfluencerName.MIN_LENGTH;

public class InfluencerNameValidationFailedException extends IllegalArgumentException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("인플루언서 이름은 최소 %d자 이상, %d자 이하로 입력해주세요.".formatted(MIN_LENGTH, MAX_LENGTH));

    public InfluencerNameValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
