package com.leesh.inflpick.v2.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.adapter.in.web.common.error.ApiErrorCode;
import com.leesh.inflpick.v2.adapter.in.web.common.dto.ApiErrorResponse;
import com.leesh.inflpick.v2.adapter.out.docs.swagger.common.ApiErrorCodeSwaggerDocs;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.HandlerMethod;

import java.time.Instant;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final Environment environment;

    @Bean
    public OpenAPI customOpenAPI() {
        List<Server> currentProfileServer = getCurrentProfileServers();
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer-Auth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .description("/oauth2/authorization/{oauth2Provider} 를 통해 소셜 로그인 진행 후 발급받은 Access Token 값을 입력하세요.")
                                .scheme("Bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("인플픽 API 명세서")
                        .description("인플픽 API 명세서입니다.")
                        .version("1.0"))
                .servers(currentProfileServer);
    }

    private @NotNull List<Server> getCurrentProfileServers() {

        String[] activeProfiles = environment.getActiveProfiles();
        Server localServer = new Server().url("http://localhost:8080")
                .description("local");
        Server prodServer = new Server()
                .url("https://api.inflpick.com")
                .description("prod");

        List<Server> currentProfileServer = new ArrayList<>();
        for (String activeProfile : activeProfiles) {
            if (activeProfile.equals("local")) {
                currentProfileServer.add(localServer);
            } else if (activeProfile.equals("prod")) {
                currentProfileServer.add(prodServer);
            }
        }
        return currentProfileServer;
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {

            ApiResponses responses = operation.getResponses();
            ApiErrorCodeSwaggerDocs methodAnnotation = handlerMethod.getMethodAnnotation(ApiErrorCodeSwaggerDocs.class);

            if (methodAnnotation == null) {
                return operation;
            }

            Class<? extends ApiErrorCode>[] values = methodAnnotation.values();
            String method = methodAnnotation.httpMethod();
            String apiPath = methodAnnotation.apiPath();
            Map<Integer, List<SwaggerConfig.SwaggerExampleHolder>> statusGroupedExampleHolders = new HashMap<>();

            for (Class<? extends ApiErrorCode> value : values) {
                ApiErrorCode[] errorCodes = value.getEnumConstants();
                List<SwaggerConfig.SwaggerExampleHolder> swaggerExampleHolders = Arrays.stream(errorCodes).map(
                        errorCode -> {
                            String reason = errorCode.getReason();
                            ApiErrorResponse apiErrorResponse = buildApiErrorResponse(errorCode, method, apiPath, reason);
                            Example example = createSwaggerExample(errorCode.getComment(), apiErrorResponse);
                            return buildSwaggerExampleHolder(errorCode, example);
                        }
                ).toList();

                for (SwaggerConfig.SwaggerExampleHolder swaggerExampleHolder : swaggerExampleHolders) {
                    statusGroupedExampleHolders.computeIfAbsent(swaggerExampleHolder.getCode(), k -> new ArrayList<>());
                    statusGroupedExampleHolders.computeIfPresent(swaggerExampleHolder.getCode(), (k, v) -> {
                        v.add(swaggerExampleHolder);
                        return v;
                    });
                }
            }

            statusGroupedExampleHolders.forEach(
                    (status, exampleHolders) -> {
                        Content content = new Content();
                        MediaType mediaType = new MediaType();

                        ApiResponse apiResponse = new ApiResponse();
                        exampleHolders.forEach(
                                swaggerExampleHolder -> {
                                    mediaType.addExamples(swaggerExampleHolder.getName(), swaggerExampleHolder.getHolder());
                                    mediaType.setSchema(new Schema<>().$ref("#/components/schemas/ApiErrorResponse"));
                                }
                        );
                        content.addMediaType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE, mediaType);
                        apiResponse.setContent(content);
                        responses.addApiResponse(status.toString(), apiResponse);
                    }
            );

            return operation;
        };
    }

    private static SwaggerExampleHolder buildSwaggerExampleHolder(ApiErrorCode errorCode, Example example) {
        return SwaggerExampleHolder.builder()
                .holder(example)
                .name(errorCode.getCode())
                .code(errorCode.getHttpStatus().value())
                .build();
    }

    private static @NotNull Example createSwaggerExample(String comment, ApiErrorResponse apiErrorResponse) {
        Example example = new Example();
        example.description(comment);
        example.value(apiErrorResponse);
        return example;
    }

    private static ApiErrorResponse buildApiErrorResponse(ApiErrorCode errorCode, String method, String apiPath, String reason) {
        return ApiErrorResponse.builder()
                .timestamp(Instant.now())
                .method(method)
                .path(apiPath)
                .code(errorCode.getCode())
                .status(errorCode.getHttpStatus().value())
                .reason(reason)
                .action(errorCode.getAction())
                .build();
    }

    @Getter
    @Builder
    public static class SwaggerExampleHolder {
        private Example holder;
        private String name;
        private int code;
    }

}
