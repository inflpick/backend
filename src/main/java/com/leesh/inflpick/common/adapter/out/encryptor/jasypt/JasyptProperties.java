package com.leesh.inflpick.common.adapter.out.encryptor.jasypt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jasypt.encryptor")
public record JasyptProperties(String password) {
}
