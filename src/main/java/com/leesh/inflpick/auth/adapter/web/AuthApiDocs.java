package com.leesh.inflpick.auth.adapter.web;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.user.adapter.in.web.TokenWebRequest;
import com.leesh.inflpick.user.adapter.in.web.TokenWebResponse;
import com.leesh.inflpick.user.adapter.in.web.AuthApiErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "인증", description = "인증 API")
public interface AuthApiDocs {

    @ApiErrorCodeSwaggerDocs(values = {AuthApiErrorCode.class}, httpMethod = "POST", apiPath = "/auth/token")
    @Operation(summary = "토큰 발급 요청",
            description = "토큰을 발급 요청합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "토큰 요청",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TokenWebRequest.class))))
    ResponseEntity<TokenWebResponse> createToken(@RequestBody TokenWebRequest tokenWebRequest);
}
