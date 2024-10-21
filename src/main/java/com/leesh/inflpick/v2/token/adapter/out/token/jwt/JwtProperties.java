package com.leesh.inflpick.v2.token.adapter.out.token.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.jwt")
public record JwtProperties(String secretKey, Integer accessTokenExpiresInSeconds, Integer refreshTokenExpiresInSeconds) {
}
