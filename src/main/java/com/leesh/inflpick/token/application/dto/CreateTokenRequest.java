package com.leesh.inflpick.token.application.dto;

import com.leesh.inflpick.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.token.adapter.out.docs.swagger.CreateTokenRequestDocs;

public record CreateTokenRequest(String grantType, String code, String refreshToken) implements CreateTokenRequestDocs {

    public CreateTokenRequest {
        RequiredFieldsValidator.validate(grantType);
    }
}
