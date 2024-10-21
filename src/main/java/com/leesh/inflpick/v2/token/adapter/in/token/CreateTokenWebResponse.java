package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.token.application.dto.TokenResponse;
import lombok.Builder;

@Builder
record CreateTokenWebResponse(String tokenType,
                                     String accessToken,
                                     Integer accessTokenExpiresInSeconds,
                                     String refreshToken,
                                     Integer refreshTokenExpiresInSeconds) implements CreateTokenWebResponseDocs {

    static CreateTokenWebResponse create(TokenResponse response) {
        return CreateTokenWebResponse.builder()
                .tokenType("Bearer")
                .accessToken(response.accessToken().value())
                .accessTokenExpiresInSeconds(response.accessToken().expiresInSeconds())
                .refreshToken(response.refreshToken().value())
                .refreshTokenExpiresInSeconds(response.refreshToken().expiresInSeconds())
                .build();
    }
}
