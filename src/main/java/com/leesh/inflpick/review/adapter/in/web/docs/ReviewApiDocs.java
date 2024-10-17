package com.leesh.inflpick.review.adapter.in.web.docs;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.CursorResponse;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.review.adapter.in.web.ReviewCreateApiErrorCode;
import com.leesh.inflpick.review.adapter.in.web.ReviewRequest;
import com.leesh.inflpick.review.adapter.in.web.ReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Tag(name = "리뷰 API", description = "리뷰 API")
public interface ReviewApiDocs {

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class, ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/reviews")
    @Operation(summary = "리뷰 페이지 조회 (Cursor 방식)", description = "리뷰 조회",
            parameters = {
                    @Parameter(name = "cursor", description = "현재 커서 (리뷰한 날짜)", example = "1970-01-01T00:00:00Z", schema = @Schema(defaultValue = "1970-01-01T00:00:00Z", implementation = Instant.class)),
                    @Parameter(name = "limit", description = "한번에 가져올 컨텐츠 수 (기본값: 20)", example = "10", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "influencerId", description = "리뷰한 인플루언서 ID", schema = @Schema(implementation = String.class)),
                    @Parameter(name = "productId", description = "리뷰된 제품 ID", schema = @Schema(implementation = String.class))
            })
    @GetMapping
    ResponseEntity<CursorResponse<ReviewResponse>> getCursorPage(@RequestParam(value = "cursor", required = false, defaultValue = "1970-01-01T00:00:00Z") Instant cursor,
                                                                        @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                                                                        @RequestParam(value = "influencerId", required = false, defaultValue = "") String influencerId,
                                                                        @RequestParam(value = "productId", required = false, defaultValue = "") String productId);

    @ApiErrorCodeSwaggerDocs(values = {ReviewCreateApiErrorCode.class, ProductReadApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "POST", apiPath = "/reviews")
    @Operation(summary = "리뷰 생성하기", description = "제품 리뷰 생성 API", security = {
            @SecurityRequirement(name = "Bearer-Auth")
    },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ReviewRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리뷰의 URI", schema = @Schema(type = "string"))),
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> review(@RequestBody ReviewRequest request);
}
