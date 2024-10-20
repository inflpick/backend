package com.leesh.inflpick.v2.adapter.out.token.jwt.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.cookie")
public record CookieProperties(String sameSite, String redirectUri, String domain, Boolean secure, String path) {
}
