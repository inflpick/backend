package com.leesh.inflpick.user.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.UnauthorizedException;
import com.leesh.inflpick.common.adapter.in.web.security.CustomOauth2User;
import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.user.core.domain.User;
import com.leesh.inflpick.user.port.out.AuthenticationToken;
import com.leesh.inflpick.user.port.out.AuthenticationTokenService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저", description = "유저 API")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthenticationTokenService tokenService;

    @ApiErrorCodeSwaggerDocs(values = {Oauth2LoginApiErrorCode.class}, httpMethod = "GET", apiPath = "/oauth2/authorization/{oauth2Type}")
    @Operation(summary = "소셜 로그인 API", description = "문서화를 위한 예시이며, 실제 동작을 위해서는 브라우저를 통해 해당 URL로 주소 입력 후 소셜 로그인을 진행합니다. 예시: \"http://{serverUrl}/oauth2/authorization/kakao 해당 링크를 브라우저 주소창에 입력\"")
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

}
