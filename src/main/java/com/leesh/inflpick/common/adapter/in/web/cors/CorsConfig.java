package com.leesh.inflpick.common.adapter.in.web.cors;

import com.leesh.inflpick.common.adapter.in.web.value.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@EnableConfigurationProperties({CorsProperties.class})
@RequiredArgsConstructor
@Configuration
public class CorsConfig {

    private final CorsProperties corsProperties;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(corsProperties.allowedOriginPatterns()));
        config.setAllowedMethods(Arrays.asList(corsProperties.allowedMethods().split(",")));
        config.setAllowedHeaders(List.of(corsProperties.allowedHeaders()));
        config.setAllowCredentials(corsProperties.allowCredentials());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 CORS 설정 적용
        return source;
    }
}
