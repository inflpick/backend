package com.leesh.inflpick.v2.adapter.in.web.token.dto.response;

import com.leesh.inflpick.v2.adapter.out.docs.swagger.auth.CreateTokenWebResponseDocs;
import com.leesh.inflpick.v2.appilcation.dto.token.TokenResponse;
import lombok.Builder;

@Builder
public record CreateTokenWebResponse(String tokenType,
                                     String accessToken,
                                     Integer accessTokenExpiresInSeconds,
                                     String refreshToken,
                                     Integer refreshTokenExpiresInSeconds) implements CreateTokenWebResponseDocs {

    public static CreateTokenWebResponse create(TokenResponse response) {
        return CreateTokenWebResponse.builder()
                .tokenType("Bearer")
                .accessToken(response.accessToken().value())
                .accessTokenExpiresInSeconds(response.accessToken().expiresInSeconds())
                .refreshToken(response.refreshToken().value())
                .refreshTokenExpiresInSeconds(response.refreshToken().expiresInSeconds())
                .build();
    }
}
