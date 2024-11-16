package com.leesh.inflpick.token.adapter.out.token.jwt.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.jwt")
public record JwtProperties(String secretKey, Integer accessTokenExpiresInSeconds, Integer refreshTokenExpiresInSeconds) {
}
