package com.leesh.inflpick.v2.review.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "리뷰 API", description = "리뷰 API")
public interface UpdateReviewControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {}, httpMethod = "PUT", apiPath = "/reviews/{id}")
    @Operation(summary = "리뷰 수정하기",
            description = "리뷰를 수정합니다.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "리뷰 ID"),
            },
            requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = ReviewRequest.class))),
            responses = {
                @ApiResponse(responseCode = "204", description = "리뷰 수정 성공 (본문 없음)")
            })
    ResponseEntity<Void> update(String id, ReviewRequest request);
}
