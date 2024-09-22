package com.leesh.inflpick.common.adapter.in.web.swagger;

import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorCode;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;

import java.time.Instant;
import java.util.*;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON, org.springframework.http.MediaType.APPLICATION_OCTET_STREAM));
        return converter;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("인플픽 API Docs")
                .description("인플픽 API 명세서입니다.")
                .version("1.0.0"));
    }


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
                            String reason = errorCode.reason();
                            ApiErrorResponse apiErrorResponse = buildApiErrorResponse(errorCode, method, apiPath, reason);
                            Example example = createSwaggerExample(errorCode.comment(), apiErrorResponse);
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
                                    mediaType.setSchema(new Schema<>().$ref("#/components/schemas/API 에러 응답"));
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
                .name(errorCode.code())
                .code(errorCode.httpStatus().value())
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
                .code(errorCode.code())
                .status(errorCode.httpStatus().value())
                .reason(reason)
                .action(errorCode.action())
                .build();
    }

    @Bean
    public GroupedOpenApi commonApi() {
        return GroupedOpenApi.builder()
                .group("공통 API")
                .pathsToMatch("/api/**")
                .pathsToExclude("/api/influencers/**", "/api/keywords/**")
                .addOperationCustomizer(customize())
                .build();
    }

    @Bean
    public GroupedOpenApi influencerApi() {
        return GroupedOpenApi.builder()
            .group("인플루언서 API")
            .pathsToMatch("/api/influencers/**")
            .addOperationCustomizer(customize())
            .build();
    }

    @Bean
    public GroupedOpenApi keywordApi() {
        return GroupedOpenApi.builder()
            .group("키워드 API")
            .pathsToMatch("/api/keywords/**")
            .addOperationCustomizer(customize())
            .build();
    }

    @Bean
    public GroupedOpenApi productApi() {
        return GroupedOpenApi.builder()
                .group("제품 API")
                .pathsToMatch("/api/products/**")
                .addOperationCustomizer(customize())
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
