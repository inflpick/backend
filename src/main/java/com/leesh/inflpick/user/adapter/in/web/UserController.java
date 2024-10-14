package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.WebOffsetPageRequest;
import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.common.adapter.in.web.security.CustomOauth2User;
import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.in.UserQueryService;
import com.leesh.inflpick.user.port.out.AuthenticationToken;
import com.leesh.inflpick.user.port.out.AuthenticationTokenService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저", description = "유저 API")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthenticationTokenService tokenService;
    private final UserQueryService userQueryService;

    @ApiErrorCodeSwaggerDocs(values = {Oauth2LoginApiErrorCode.class}, httpMethod = "GET", apiPath = "/oauth2/authorization/{oauth2Type}")
    @Operation(summary = "소셜 로그인 API",
            description = "문서화를 위한 예시이며, 실제 동작을 위해서는 브라우저를 통해 해당 URL로 주소 입력 후 소셜 로그인을 진행합니다. 예시: \"http://{serverUrl}/oauth2/authorization/kakao 해당 링크를 브라우저 주소창에 입력\"")
    @ApiResponse(
            responseCode = "200",
            description = "소셜 로그인 성공",
            content = @io.swagger.v3.oas.annotations.media.Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    @GetMapping(path = "/oauth2/authorization/{oauth2Type}")
    public ResponseEntity<LoginResponse> oauth2Authorization(
            @Parameter(description = "OAuth2 타입 (kakao, naver, google 중 하나)", required = true, example = "kakao", allowReserved = true)
            @PathVariable String oauth2Type) {
        return ResponseEntity.ok().build();
    }

    @Hidden
    @GetMapping(path = "/loginSuccess")
    public ResponseEntity<LoginResponse> loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {

        if (oAuth2User == null) {
            throw new UnauthorizedException("로그인에 실패하였습니다.");
        }

        User user = ((CustomOauth2User) oAuth2User).user();
        AuthenticationToken accessToken = tokenService.createAccessToken(user);
        AuthenticationToken refreshToken = tokenService.createRefreshToken(user);
        LoginResponse response = LoginResponse.of(accessToken, refreshToken);
        return ResponseEntity.ok().body(response);
    }

    @Hidden
    @GetMapping(path = "/loginFailure")
    public ResponseEntity<LoginResponse> loginFailure() {
        throw new UnauthorizedException("로그인에 실패하였습니다.");
    }

    @Operation(summary = "인플루언서 목록 조회",
            description = "인플루언서 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (여러개 가능) [name | createdDate | lastModifiedDate] 중 하나 (기본값: createdDate,asc)", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "인플루언서 이름 기준 오름차순 정렬")
                            })
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebPageResponse<UserWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0")
                                                                 Integer page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                 Integer size,
                                                                 @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                 String[] sort) {

        PageRequest request = new WebOffsetPageRequest(page, size, sort);
        PageResponse<User> pageResponse = userQueryService.query(request);
        UserWebResponse[] webContents = pageResponse.contents().stream()
                .map(UserWebResponse::from)
                .toList()
                .toArray(UserWebResponse[]::new);
        WebPageResponse<UserWebResponse> response = WebPageResponse.of(webContents, pageResponse);
        return ResponseEntity.ok()
                .body(response);
    }

}
