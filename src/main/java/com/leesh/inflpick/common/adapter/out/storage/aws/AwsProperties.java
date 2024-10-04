package com.leesh.inflpick.common.adapter.out.storage.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@ConfigurationProperties(prefix = "custom.storage.aws")
public record AwsProperties(Credentials credentials, S3 s3) {

    public record Credentials(String accessKey, String secretKey) {
    }

    public record S3(String region, String bucketName) {
    }

}
