package com.leesh.inflpick.common.adapter.out.storage.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Profile("prod")
@RequiredArgsConstructor
@EnableConfigurationProperties(AwsProperties.class)
@Configuration
public class AwsS3Config {

    @Bean
    public S3Client awsS3Client(AwsProperties awsProperties) {
        Region region = Region.of(awsProperties.region());
        return S3Client.builder()
                .region(region)
                .build();
    }

}
