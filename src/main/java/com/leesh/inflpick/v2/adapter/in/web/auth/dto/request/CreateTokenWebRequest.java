package com.leesh.inflpick.v2.adapter.in.web.auth.dto.request;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.v2.adapter.out.docs.swagger.auth.CreateTokenWebRequestDocs;

public record CreateTokenWebRequest(String grantType, String code, String refreshToken) implements CreateTokenWebRequestDocs {

    public CreateTokenWebRequest {
        RequiredFieldsValidator.validate(grantType, code, refreshToken);
    }
}
