package com.leesh.inflpick.v2.token.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.token.adapter.in.web.exception.NotSupportedGrantTypeException;
import com.leesh.inflpick.v2.token.application.dto.CreateTokenRequest;
import com.leesh.inflpick.v2.token.application.dto.CreateTokenResponse;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredAuthenticationCodeException;
import com.leesh.inflpick.v2.token.application.port.in.exception.ExpiredRefreshTokenException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "인증 API", description = "인증 API")
public interface CreateTokenControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {NotSupportedGrantTypeException.class, ExpiredRefreshTokenException.class, ExpiredAuthenticationCodeException.class}, httpMethod = "POST", apiPath = "/auth/token")
    @Operation(summary = "토큰 발급하기",
            description = "인증 코드 혹은 갱신 토큰을 통해 토큰을 발급합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateTokenRequest.class))
            ))
    ResponseEntity<CreateTokenResponse> createToken(CreateTokenRequest request);

}
