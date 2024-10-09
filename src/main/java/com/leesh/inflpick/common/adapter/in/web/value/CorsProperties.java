package com.leesh.inflpick.common.adapter.in.web.value;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.cors")
public record CorsProperties(String allowedOriginPatterns, String allowedMethods, String allowedHeaders, boolean allowCredentials) {
}
