package com.leesh.inflpick.influencer.core.domain;

import java.text.MessageFormat;

import static com.leesh.inflpick.influencer.core.domain.InfluencerIntroduction.MAX_LENGTH;
import static com.leesh.inflpick.influencer.core.domain.InfluencerIntroduction.MIN_LENGTH;

public class InfluencerIntroductionValidationFailedException extends RuntimeException {

    public static final MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("인플루언서 소개는 최소 %d자 이상, %d자 이하로 입력해주세요. 현재 입력값: {0}".formatted(MIN_LENGTH, MAX_LENGTH));

    public InfluencerIntroductionValidationFailedException(String input) {
        super(ERROR_MESSAGE_FORMAT.format(new Object[]{input}));
    }
}
