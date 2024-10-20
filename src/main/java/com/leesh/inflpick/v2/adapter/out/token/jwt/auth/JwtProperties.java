package com.leesh.inflpick.v2.adapter.out.token.jwt.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.jwt")
public record JwtProperties(String secretKey, Integer accessTokenExpiresInSeconds, Integer refreshTokenExpiresInSeconds) {
}
