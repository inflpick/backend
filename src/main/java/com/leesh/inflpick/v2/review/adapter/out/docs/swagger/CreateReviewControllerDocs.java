package com.leesh.inflpick.v2.review.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.review.adapter.in.web.constant.CreateReviewApiErrorCode;
import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "리뷰 API", description = "리뷰 API")
public interface CreateReviewControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {CreateReviewApiErrorCode.class}, httpMethod = "POST", apiPath = "/reviews")
    @Operation(summary = "리뷰 등록하기",
            description = "리뷰를 등록합니다.",
            security = {
                @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ReviewRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "리뷰 등록 성공", headers = {
                            @Header(name = "Location", description = "생성된 제품 ID", schema = @Schema(type = "string", implementation = String.class))
                    })}
    )
    ResponseEntity<Void> create(ReviewRequest request);

}
