package com.leesh.inflpick.review.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.application.dto.CursorResponse;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.review.application.dto.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Tag(name = "리뷰 API", description = "리뷰 API")
public interface GetReviewControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {}, httpMethod = "GET", apiPath = "/reviews/{id}")
    @Operation(summary = "리뷰 단건 조회",
            description = "리뷰를 조회합니다.",
            parameters = {
                @Parameter(name = "id", description = "리뷰 ID", required = true, example = "6726946b272157735138c837", schema = @Schema(implementation = String.class))
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = ReviewResponse.class)))
            })
    ResponseEntity<ReviewResponse> get(String id);

    @Operation(summary = "리뷰 목록 커서 조회",
            description = "리뷰 목록 커서를 조회합니다.",
            parameters = {
                @Parameter(name = "cursor", description = "리뷰한 날짜 (기본값: 1970-01-01T00:00:00Z)", example = "1970-01-01T00:00:00Z", schema = @Schema(implementation = Instant.class)),
                @Parameter(name = "limit", description = "커서 한번에 컨텐츠 사이즈 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                @Parameter(name = "influencerId", description = "인플루언서 ID (기본값: \"\")", schema = @Schema(implementation = String.class)),
                @Parameter(name = "productId", description = "제품 ID (기본값: \"\")", schema = @Schema(implementation = String.class)),
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
            })
    ResponseEntity<CursorResponse<ReviewResponse>> getCursor(@RequestParam(value = "cursor", required = false, defaultValue = "1970-01-01T00:00:00Z") Instant cursor,
                                                             @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                                                             @RequestParam(value = "influencerId", required = false, defaultValue = "") String influencerId,
                                                             @RequestParam(value = "productId", required = false, defaultValue = "") String productId);
}
