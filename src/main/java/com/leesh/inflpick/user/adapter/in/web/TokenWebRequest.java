package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;

public record TokenWebRequest(
        String grantType,
        String code) implements TokenRequestWebRequestApiDocs {

    public TokenWebRequest {
        RequiredFieldsValidator.validate(grantType, code);
    }
}
