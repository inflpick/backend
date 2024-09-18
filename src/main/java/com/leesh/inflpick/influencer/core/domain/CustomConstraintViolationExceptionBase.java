package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.common.adapter.in.web.ApiErrorCode;
import com.leesh.inflpick.common.adapter.in.web.BaseBusinessException;

public class CustomConstraintViolationExceptionBase extends BaseBusinessException {
    public CustomConstraintViolationExceptionBase(String message, ApiErrorCode apiErrorCode) {
        super(message, apiErrorCode);
    }
}
