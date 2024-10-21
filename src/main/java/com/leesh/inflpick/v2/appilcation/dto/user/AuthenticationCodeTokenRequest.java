package com.leesh.inflpick.v2.appilcation.dto.user;

import com.leesh.inflpick.v2.domain.token.vo.GrantType;
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
