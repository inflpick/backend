package com.leesh.inflpick.influencer.core.domain.exception;

import com.leesh.inflpick.influencer.port.InfluencerSortType;

import java.text.MessageFormat;

public class InvalidInfluencerSortTypeException extends RuntimeException {

    public static MessageFormat ERROR_MESSAGE_FORMAT = new MessageFormat("정렬 타입은 %s 중 하나여야 합니다.".formatted(String.join(", ", InfluencerSortType.availableSortTypes())));

    public InvalidInfluencerSortTypeException(String input) {
        super(ERROR_MESSAGE_FORMAT.toPattern() + input);
    }
}
