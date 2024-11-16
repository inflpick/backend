package com.leesh.inflpick.common.adapter.out.persistence.mongo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import java.util.Optional;

@RequiredArgsConstructor
@Configuration
@EnableMongoAuditing
public class MongoAuditingConfig {

    private final HttpServletRequest request;

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            if (request == null) {
                return Optional.of("system");
            }
            // IP 주소를 반환
            return Optional.of(getClientIp(request));
        };
    }

    // 클라이언트 IP를 추출하는 메서드
    private static String getClientIp(HttpServletRequest request) {
        try {
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null || ipAddress.isEmpty()) {
                ipAddress = request.getRemoteAddr();  // 기본적으로 RemoteAddr에서 IP를 가져옴
            }
            return ipAddress;
        } catch (Exception e) {
            return "system";
        }
    }

}
