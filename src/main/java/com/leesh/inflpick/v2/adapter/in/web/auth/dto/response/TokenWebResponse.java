package com.leesh.inflpick.v2.adapter.in.web.auth.dto.response;

import com.leesh.inflpick.v2.domain.token.vo.Token;
import lombok.Builder;

@Builder
public record TokenWebResponse(String tokenType,
                               String accessToken,
                               Integer accessTokenExpiresInSeconds,
                               String refreshToken,
                               Integer refreshTokenExpiresInSeconds) implements TokenWebResponseDocs {

    public static TokenWebResponse create(Token accessToken, Token refreshToken) {
        return TokenWebResponse.builder()
                .tokenType("Bearer")
                .accessToken(accessToken.value())
                .accessTokenExpiresInSeconds(accessToken.expiresInSeconds())
                .refreshToken(refreshToken.value())
                .refreshTokenExpiresInSeconds(refreshToken.expiresInSeconds())
                .build();
    }
}
