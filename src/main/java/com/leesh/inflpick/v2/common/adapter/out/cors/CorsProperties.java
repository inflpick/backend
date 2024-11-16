package com.leesh.inflpick.v2.common.adapter.out.cors;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "custom.cors")
public record CorsProperties(List<String> allowedOriginPatterns, List<String> allowedMethods, List<String> allowedHeaders, boolean allowCredentials) {
}
