package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.core.domain.ConstraintViolationException;

public class UserInputException extends RuntimeException {
    public UserInputException(String cause, String input) {
        super("잘못된 입력값 입니다. 원인: %s, 입력값: %s".formatted(cause, input));
    }

    public UserInputException(ConstraintViolationException e) {
        super("잘못된 입력값 입니다. 원인: %s".formatted(e.getMessage()));
    }
}
