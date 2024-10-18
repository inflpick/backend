package com.leesh.inflpick.user.adapter.out.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.cookie")
public record CookieProperties(String sameSite, String redirectUri, String domain, Boolean secure, String path) {
}
