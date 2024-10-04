package com.leesh.inflpick.review.adapter.in.web;


import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CursorResponse;
import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCommand;
import com.leesh.inflpick.review.port.ReviewCursorQuery;
import com.leesh.inflpick.review.port.in.ReviewCommandService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@Tag(name = "리뷰 API", description = "리뷰 API")
@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/reviews")
@RestController
public class ReviewController {

    private final InfluencerQueryService influencerQueryService;
    private final ProductQueryService productQueryService;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class, ProductReadApiErrorCode.class})
    @Operation(summary = "리뷰 페이지 조회 (Cursor 방식)", description = "리뷰 조회")
    @GetMapping
    public ResponseEntity<CursorResponse<ReviewResponse>> getCursorPage(@Parameter(description = "현재 커서 (UTC 형식의 날짜 포맷)", example = "2021-07-01T00:00:00Z", schema = @Schema(defaultValue = "1970-01-01T00:00:00Z", implementation = Instant.class))
                                                                @RequestParam(value = "cursor", required = false, defaultValue = "1970-01-01T00:00:00Z")
                                                                Instant cursor,
                                                                @Parameter(description = "한번에 가져올 컨텐츠 수 (기본값: 20)", example = "10", schema = @Schema(implementation = Integer.class))
                                                                @RequestParam(value = "limit", required = false, defaultValue = "20")
                                                                Integer limit,
                                                                @Parameter(description = "리뷰한 인플루언서 ID", example = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c4d", schema = @Schema(implementation = String.class))
                                                                @RequestParam(value = "influencerId", required = false)
                                                                String influencerId,
                                                                @Parameter(description = "리뷰된 제품 ID", example = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c4d", schema = @Schema(implementation = String.class))
                                                                @RequestParam(value = "productId", required = false)
                                                                String productId) {

        ReviewCursorQuery query = new ReviewCursorQuery(influencerId, productId, cursor, limit);
        CursorPage<Review> page = reviewQueryService.getCursorPage(query);
        List<ReviewResponse> contents = page.contents().stream()
                .map(ReviewResponse::from)
                .toList();
        CursorResponse<ReviewResponse> response = new CursorResponse<>(page.limit(), contents, page.hasNext());
        return ResponseEntity.ok(response);
    }

    @ApiErrorCodeSwaggerDocs(values = {ReviewCreateApiErrorCode.class, ProductReadApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "POST", apiPath = "/api/reviews")
    @Operation(summary = "리뷰 생성하기", description = "제품 리뷰 생성 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리뷰의 URI", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "입력 값이 잘못된 경우", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> review(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리뷰 생성 요청 정보", required = true)
                                       @RequestBody
                                       ReviewRequest request) {

        Influencer reviewer = influencerQueryService.getById(request.influencerId());
        Product product = productQueryService.getById(request.productId());
        ReviewCommand command = request.toCommand();
        String reviewId = reviewCommandService.create(reviewer, product, command);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{reviewId}")
                .buildAndExpand(reviewId)
                .toUri();
        return ResponseEntity.created(location)
                .header(HttpHeaders.ACCEPT, MediaType.MULTIPART_FORM_DATA_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
