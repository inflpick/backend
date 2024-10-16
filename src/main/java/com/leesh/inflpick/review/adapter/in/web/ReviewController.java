package com.leesh.inflpick.review.adapter.in.web;


import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.CursorResponse;
import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.adapter.in.web.value.ProductReadApiErrorCode;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final StorageService storageService;

    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class, ProductReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/reviews")
    @Operation(summary = "리뷰 페이지 조회 (Cursor 방식)", description = "리뷰 조회",
            parameters = {
                    @Parameter(name = "cursor", description = "현재 커서 (리뷰한 날짜)", example = "1970-01-01T00:00:00Z", schema = @Schema(defaultValue = "1970-01-01T00:00:00Z", implementation = Instant.class)),
                    @Parameter(name = "limit", description = "한번에 가져올 컨텐츠 수 (기본값: 20)", example = "10", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "influencerId", description = "리뷰한 인플루언서 ID", schema = @Schema(implementation = String.class)),
                    @Parameter(name = "productId", description = "리뷰된 제품 ID", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = CursorResponse.class)))
            })
    @GetMapping
    public ResponseEntity<CursorResponse<ReviewResponse>> getCursorPage(@RequestParam(value = "cursor", required = false, defaultValue = "1970-01-01T00:00:00Z") Instant cursor,
                                                                        @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                                                                        @RequestParam(value = "influencerId", required = false, defaultValue = "") String influencerId,
                                                                        @RequestParam(value = "productId", required = false, defaultValue = "") String productId) {

        ReviewCursorQuery query = new ReviewCursorQuery(influencerId, productId, cursor, limit);
        CursorPage<Review> page = reviewQueryService.getCursorPage(query);
        List<ReviewResponse> contents = page.contents().stream()
                .map(review -> {
                    String reviewerProfileImageUrl = storageService.getUrlString(review.getReviewer().getProfileImagePath());
                    String productImageUrl = storageService.getUrlString(review.getProduct().getProductImagePath());
                    return ReviewResponse.from(review, InfluencerWebResponse.from(
                            influencerQueryService.query(review.getReviewer().getId()),
                            reviewerProfileImageUrl
                    ), ProductWebResponse.from(
                            productQueryService.query(review.getProduct().getId()),
                            productImageUrl
                    ));
                })
                .toList();
        CursorResponse<ReviewResponse> response = new CursorResponse<>(page.limit(), contents, page.hasNext());
        return ResponseEntity.ok(response);
    }

    @ApiErrorCodeSwaggerDocs(values = {ReviewCreateApiErrorCode.class, ProductReadApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "POST", apiPath = "/reviews")
    @Operation(summary = "리뷰 생성하기", description = "제품 리뷰 생성 API", security = {
            @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = ReviewRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리뷰의 URI", schema = @Schema(type = "string"))),
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> review(@RequestBody ReviewRequest request) {

        Influencer reviewer = influencerQueryService.query(request.influencerId());
        Product product = productQueryService.query(request.productId());
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
