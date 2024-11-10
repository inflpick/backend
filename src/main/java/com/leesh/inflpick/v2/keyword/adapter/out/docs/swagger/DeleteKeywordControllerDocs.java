package com.leesh.inflpick.v2.keyword.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "키워드 API", description = "키워드 API")
public interface DeleteKeywordControllerDocs {

    @Operation(summary = "키워드 삭제하기",
            description = "키워드를 삭제합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "키워드 ID"),
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "키워드 삭제 성공 (본문 없음)")
            })
    ResponseEntity<Void> delete(String keywordId);
}
