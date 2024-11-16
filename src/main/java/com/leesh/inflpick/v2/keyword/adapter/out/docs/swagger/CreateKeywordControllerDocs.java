package com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.v2.keyword.application.exception.KeywordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "키워드 API", description = "키워드 API")
public interface CreateKeywordControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {AlreadyExistKeywordNameException.class, KeywordNotFoundException.class}, httpMethod = "POST", apiPath = "/keyword")
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
