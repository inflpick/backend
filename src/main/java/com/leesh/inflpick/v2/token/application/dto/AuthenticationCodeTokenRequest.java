package com.leesh.inflpick.v2.token.application.dto;

import com.leesh.inflpick.v2.token.domain.vo.GrantType;
import lombok.Getter;

@Getter
public class AuthenticationCodeTokenRequest {

    private final GrantType grantType = GrantType.AUTHENTICATION_CODE;
    private final String code;

    private AuthenticationCodeTokenRequest(String code) {
        this.code = code;
    }

    public static AuthenticationCodeTokenRequest create(String code) {
        return new AuthenticationCodeTokenRequest(code);
    }

}
