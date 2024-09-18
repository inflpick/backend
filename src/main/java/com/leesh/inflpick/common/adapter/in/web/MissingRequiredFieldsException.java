package com.leesh.inflpick.common.adapter.in.web;

public class MissingRequiredFieldsException extends BaseBusinessException {
    public MissingRequiredFieldsException(String logMessage) {
        super(logMessage, CommonApiErrorCode.MISSING_REQUIRED_FIELDS);
    }
}
