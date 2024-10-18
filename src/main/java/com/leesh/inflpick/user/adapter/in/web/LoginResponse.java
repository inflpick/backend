package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.user.port.out.Token;
import lombok.Builder;

@Builder
public record LoginResponse(String tokenType,
                            String accessToken,
                            Integer accessTokenExpiresInSeconds,
                            String refreshToken,
                            Integer refreshTokenExpiresInSeconds) implements LoginResponseApiDocs {

        public static LoginResponse of(Token accessToken, Token refreshToken) {
                return LoginResponse.builder()
                        .tokenType("Bearer")
                        .accessToken(accessToken.value())
                        .accessTokenExpiresInSeconds(accessToken.expiresInSeconds())
                        .refreshToken(refreshToken.value())
                        .refreshTokenExpiresInSeconds(refreshToken.expiresInSeconds())
                        .build();
        }
}
