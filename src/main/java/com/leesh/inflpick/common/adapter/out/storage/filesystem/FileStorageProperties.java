package com.leesh.inflpick.common.adapter.out.storage.filesystem;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom.storage.file")
public record FileStorageProperties(String baseUri) {
}
