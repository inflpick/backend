package com.leesh.inflpick.v2.adapter.in.web.auth.dto.request;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;

public record TokenWebRequest(
        String grantType,
        String code) implements TokenWebRequestDocs {

    public TokenWebRequest {
        RequiredFieldsValidator.validate(grantType, code);
    }
}
