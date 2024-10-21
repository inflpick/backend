package com.leesh.inflpick.v2.adapter.out.docs.swagger.auth;

import com.leesh.inflpick.v2.adapter.in.web.token.dto.request.Oauth2ProviderWebRequest;
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
public interface AuthControllerDocs {

    @Operation(summary = "인증 코드 받기",
        description = "해당 링크로 이동하여 소셜 로그인 진행 후 URL 파라미터를 통해 인증 코드를 받습니다.",
        parameters = {
            @Parameter(name = "provider", required = true, schema = @Schema(example = "GOOGLE", implementation = Oauth2ProviderWebRequest.class))
        },
        responses = {
            @ApiResponse(
                responseCode = "302",
                description = "Location 헤더로 리다이렉션 응답",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE),
                headers = {
                    @Header(name = "Location", description = "리다이렉션 URL", schema = @Schema(example = "https://inflpick.com/oauth2/redirect?code=7a726599-abfe-4eaa-91f8-322607303ba0"))
        })
    })
    ResponseEntity<Void> oauth2Authorization(@PathVariable String provider);


}
