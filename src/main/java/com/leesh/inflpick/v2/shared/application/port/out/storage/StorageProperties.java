package com.leesh.inflpick.v2.shared.application.port.out.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.storage")
public record StorageProperties(Uri uri) {

    public record Uri(String scheme, String host, String basePath) {
    }
}
