package com.leesh.inflpick.v2.adapter.in.web.auth;

import com.leesh.inflpick.v2.adapter.in.web.auth.dto.request.TokenWebRequest;
import com.leesh.inflpick.v2.adapter.in.web.auth.dto.response.TokenWebResponse;
import com.leesh.inflpick.v2.domain.user.vo.Oauth2Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "인증 API", description = "인증 API")
interface AuthControllerDocs {

    @Operation(summary = "소셜 로그인 API",
            description = "문서화를 위한 예시이며, 실제 동작을 위해서는 브라우저를 통해 해당 URL로 주소 입력 후 소셜 로그인을 진행합니다. 예시: \"http://{serverUrl}/oauth2/authorization/kakao 해당 링크를 브라우저 주소창에 입력\"",
            parameters = {
                    @Parameter(name = "oauth2Provider", required = true, schema = @Schema(example = "GOOGLE", implementation = Oauth2Provider.class))
            },
            responses = {
                    @ApiResponse(
                            responseCode = "302",
                            description = "Location 헤더로 리다이렉션 응답",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                            headers = {
                                    @Header(name = "Location", description = "리다이렉션 URL", example = "http://inflpick.com/oauth2/redirect"),
                                    @Header(name = "Set-Cookie", description = "토큰을 발행하기 위한 인증 코드", example = "AUTHORIZATION_CODE={authorization_code}; Path=/; HttpOnly"),
                            })
            })
    ResponseEntity<Void> oauth2Authorization(@PathVariable String oauth2Provider);

    @Operation(summary = "토큰 발급 API",
            description = "인증 코드로 토큰을 발급합니다.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "토큰 발급 성공",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TokenWebResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "잘못된 요청",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            })
    ResponseEntity<TokenWebResponse> createToken(TokenWebRequest request);

}
