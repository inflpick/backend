package com.leesh.inflpick.v2.token.application.dto;

import com.leesh.inflpick.v2.token.domain.vo.Token;

public record TokenResponse(Token accessToken, Token refreshToken) {

    public static TokenResponse create(Token accessToken, Token refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
}
