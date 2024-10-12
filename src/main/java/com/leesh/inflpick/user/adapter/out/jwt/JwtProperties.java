package com.leesh.inflpick.user.adapter.out.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.jwt")
public record JwtProperties(
        String secretKey,
        Integer accessTokenExpiresInSeconds,
        Integer refreshTokenExpiresInSeconds) {
}
