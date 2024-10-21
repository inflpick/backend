package com.leesh.inflpick.v2.adapter.in.web.auth.dto.response;

import com.leesh.inflpick.v2.adapter.out.docs.swagger.auth.TokenWebResponseDocs;
import com.leesh.inflpick.v2.appilcation.dto.user.TokenResponse;
import lombok.Builder;

@Builder
public record TokenWebResponse(String tokenType,
                               String accessToken,
                               Integer accessTokenExpiresInSeconds,
                               String refreshToken,
                               Integer refreshTokenExpiresInSeconds) implements TokenWebResponseDocs {

    public static TokenWebResponse create(TokenResponse response) {
        return TokenWebResponse.builder()
                .tokenType("Bearer")
                .accessToken(response.accessToken().value())
                .accessTokenExpiresInSeconds(response.accessToken().expiresInSeconds())
                .refreshToken(response.refreshToken().value())
                .refreshTokenExpiresInSeconds(response.refreshToken().expiresInSeconds())
                .build();
    }
}
