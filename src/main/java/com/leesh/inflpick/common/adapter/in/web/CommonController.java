package com.leesh.inflpick.common.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공통 API", description = "API 요청 시 발생하는 공통적인 에러 응답을 명시합니다.")
@RestController
public class CommonController {

    @ApiErrorCodeSwaggerDocs(values = {CommonApiErrorCode.class}, httpMethod = "*", apiPath = "/api/**")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @Operation(summary = "공통 API 에러 응답", description = "공통 API 에러 응답을 명시합니다.")
    @GetMapping(path = "/api")
    public ResponseEntity<Void> common() {
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Object email = oAuth2User.getAttribute("email");
        return "HELLO";
    }
}
