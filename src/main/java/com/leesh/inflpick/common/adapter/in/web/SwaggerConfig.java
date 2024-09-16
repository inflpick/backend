package com.leesh.inflpick.common.adapter.in.web;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("인플픽 API Docs")
                .description("인플픽 API 명세서입니다.")
                .version("1.0.0"));
    }

    @Bean
    public GroupedOpenApi influencerApi() {
        return GroupedOpenApi.builder()
            .group("인플루언서 API")
            .pathsToMatch("/api/influencers/**")
            .build();
    }

}
