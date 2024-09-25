package com.leesh.inflpick.review.adapter.in.web;


import com.leesh.inflpick.review.port.in.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "리뷰 API", description = "리뷰 API")
@Builder
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "리뷰 조회", description = "리뷰 조회")
    @GetMapping("/reviews")
    public ResponseEntity<?> list(@Parameter(description = "현재 커서 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class))
                                  @RequestParam(value = "cursor", required = false, defaultValue = "0")
                                  Integer cursor,
                                  @Parameter(description = "커서 크기 (기본값: 20)", example = "10", schema = @Schema(implementation = Integer.class))
                                  @RequestParam(value = "limit", required = false, defaultValue = "20")
                                  Integer limit,
                                  @Parameter(description = "리뷰한 인플루언서 ID", example = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c4d", schema = @Schema(implementation = String.class))
                                  @RequestParam(value = "influencerId", required = false)
                                  String influencerId,
                                  @Parameter(description = "리뷰된 제품 ID", example = "9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c4d", schema = @Schema(implementation = String.class))
                                  @RequestParam(value = "productId", required = false)
                                  String productId) {
        CursorRequest cursorRequest = new CursorRequest(cursor, limit, influencerId, productId);
        reviewQueryService.getSlice(cursorRequest);
        return ResponseEntity.ok(reviewQueryService.list());
    }

}
