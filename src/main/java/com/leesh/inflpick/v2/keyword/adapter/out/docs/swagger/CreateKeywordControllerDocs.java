package com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.keyword.adapter.in.web.controller.constant.CommonKeywordApiErrorCode;
import com.leesh.inflpick.v2.keyword.adapter.in.web.controller.constant.CreateKeywordApiErrorCode;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "키워드 API", description = "키워드 API")
public interface CreateKeywordControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {CommonKeywordApiErrorCode.class, CreateKeywordApiErrorCode.class}, httpMethod = "POST", apiPath = "/keyword")
    @Operation(summary = "키워드 등록하기",
            description = "키워드를 등록합니다.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = KeywordRequest.class)
            )
    ))
    ResponseEntity<Void> create(KeywordRequest request);
}
