package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "유저", description = "유저 API")
public interface UserApiDocs {

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

    @Operation(summary = "유저 목록 조회",
            description = "유저 목록을 조회합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc, 다중 정렬 가능), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "인플루언서 이름 기준 오름차순 정렬")
                            })
            })
    ResponseEntity<WebPageResponse<UserWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                          Integer page,
                                                          @RequestParam(name = "size", required = false, defaultValue = "20")
                                                          Integer size,
                                                          @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                          String[] sort);

    @Operation(summary = "유저 프로필 조회",
            description = "유저 프로필을 조회합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            })
    ResponseEntity<UserWebResponse> me(@AuthenticationPrincipal UserDetails userDetails);
}
