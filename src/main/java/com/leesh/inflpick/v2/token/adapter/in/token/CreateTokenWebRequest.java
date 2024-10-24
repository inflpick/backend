package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;

record CreateTokenWebRequest(String grantType, String code, String refreshToken) implements CreateTokenWebRequestDocs {

    CreateTokenWebRequest {
        RequiredFieldsValidator.validate(grantType);
    }
}
