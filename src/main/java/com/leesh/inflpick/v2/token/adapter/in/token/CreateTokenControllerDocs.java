package com.leesh.inflpick.v2.token.adapter.in.token;

import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCodeSwaggerDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "인증 API", description = "인증 API")
interface CreateTokenControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {TokenApiErrorCode.class}, httpMethod = "POST", apiPath = "/auth/token")
    @Operation(summary = "토큰 발급하기",
            description = "토큰을 발급합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateTokenWebRequest.class))
            ))
    ResponseEntity<CreateTokenWebResponse> createToken(CreateTokenWebRequest request);

}
