package com.leesh.inflpick.v2.token.application.dto;

import com.leesh.inflpick.v2.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.v2.token.adapter.out.docs.swagger.CreateTokenRequestDocs;

public record CreateTokenRequest(String grantType, String code, String refreshToken) implements CreateTokenRequestDocs {

    public CreateTokenRequest {
        RequiredFieldsValidator.validate(grantType);
    }
}
