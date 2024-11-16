package com.leesh.inflpick.token.adapter.out.token.jwt.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.auth")
public record AuthProperties(String redirectUri) {
}
