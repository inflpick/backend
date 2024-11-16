package com.leesh.inflpick.token.application.dto;

import com.leesh.inflpick.token.adapter.out.docs.swagger.CreateTokenResponseDocs;
import com.leesh.inflpick.token.domain.Token;

public record CreateTokenResponse(String tokenType,
                                  String accessToken,
                                  Integer accessTokenExpiresInSeconds,
                                  String refreshToken,
                                  Integer refreshTokenExpiresInSeconds) implements CreateTokenResponseDocs {

    public static CreateTokenResponse create(Token accessToken, Token refreshToken) {
        return new CreateTokenResponse("Bearer",
                accessToken.value(),
                accessToken.expiresInSeconds(),
                refreshToken.value(),
                refreshToken.expiresInSeconds());
    }
}
