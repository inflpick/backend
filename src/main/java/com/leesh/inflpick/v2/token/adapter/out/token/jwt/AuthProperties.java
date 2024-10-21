package com.leesh.inflpick.v2.token.adapter.out.token.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.auth")
public record AuthProperties(String redirectUri) {
}
