package com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordRequest;
import com.leesh.inflpick.v2.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.v2.keyword.application.exception.KeywordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "키워드 API", description = "키워드 API")
public interface UpdateKeywordControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {AlreadyExistKeywordNameException.class, KeywordNotFoundException.class}, httpMethod = "PUT", apiPath = "/keyword")
    @Operation(summary = "키워드 수정하기",
            description = "키워드를 수정합니다.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                @Parameter(name = "id", required = true, example = "6726946b272157735138c837", description = "키워드 ID"),
            },
            responses = {
                @ApiResponse(responseCode = "204", description = "키워드 수정 성공", content = @io.swagger.v3.oas.annotations.media.Content(schema = @io.swagger.v3.oas.annotations.media.Schema()))
            })
    ResponseEntity<Void> update(String keywordId, KeywordRequest request);
}
