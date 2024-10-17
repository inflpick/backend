package com.leesh.inflpick.common.adapter.in.web.docs;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import com.leesh.inflpick.user.adapter.in.web.LoginResponse;
import com.leesh.inflpick.user.adapter.in.web.Oauth2LoginApiErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "공통 API", description = "공통 API")
public interface CommonApiDocs {

    @ApiErrorCodeSwaggerDocs(values = {CommonApiErrorCode.class}, httpMethod = "*", apiPath = "/api/**")
    @Operation(summary = "공통 API 에러 응답",
            description = "공통 API 에러 응답을 명시합니다.")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    ResponseEntity<Void> common();

    @ApiErrorCodeSwaggerDocs(values = {Oauth2LoginApiErrorCode.class}, httpMethod = "GET", apiPath = "/oauth2/authorization/{oauth2Type}")
    @Operation(summary = "소셜 로그인 API",
            description = "문서화를 위한 예시이며, 실제 동작을 위해서는 브라우저를 통해 해당 URL로 주소 입력 후 소셜 로그인을 진행합니다. 예시: \"http://{serverUrl}/oauth2/authorization/kakao 해당 링크를 브라우저 주소창에 입력\"",
            parameters = {
                    @Parameter(name = "oauth2Type", description = "OAuth2 타입 (kakao, naver, google 중 하나)", required = true, example = "google")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "소셜 로그인 성공",
                            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    ResponseEntity<LoginResponse> oauth2Authorization(@PathVariable String oauth2Type);

}
