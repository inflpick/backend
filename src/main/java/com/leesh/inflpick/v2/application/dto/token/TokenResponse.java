package com.leesh.inflpick.v2.application.dto.token;

import com.leesh.inflpick.v2.domain.token.vo.Token;

public record TokenResponse(Token accessToken, Token refreshToken) {

    public static TokenResponse create(Token accessToken, Token refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
}
