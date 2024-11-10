package com.leesh.inflpick.v2.review.adapter.in.web.controller;

import com.leesh.inflpick.v2.common.application.dto.CursorResponse;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.adapter.out.docs.swagger.GetReviewControllerDocs;
import com.leesh.inflpick.v2.review.application.dto.CursorRequest;
import com.leesh.inflpick.v2.review.application.dto.ReviewResponse;
import com.leesh.inflpick.v2.review.application.port.in.GetReviewUseCase;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
public class GetReviewController implements GetReviewControllerDocs {

    private final GetReviewUseCase getReviewUseCase;

    @GetMapping(value = "/reviews/{id}", produces = "application/json")
    public ResponseEntity<ReviewResponse> get(@PathVariable(value = "id") String id) {
        ReviewId reviewId = ReviewId.create(id);
        ReviewResponse response = getReviewUseCase.get(reviewId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/reviews", produces = "application/json")
    public ResponseEntity<CursorResponse<ReviewResponse>> getCursor(@RequestParam(value = "cursor", required = false, defaultValue = "1970-01-01T00:00:00Z") Instant cursor,
                                                                        @RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
                                                                        @RequestParam(value = "influencerId", required = false, defaultValue = "") String influencerId,
                                                                        @RequestParam(value = "productId", required = false, defaultValue = "") String productId) {
        InfluencerId influencerIdObject = InfluencerId.create(influencerId);
        ProductId productIdObject = ProductId.create(productId);
        CursorRequest request = new CursorRequest(influencerIdObject, productIdObject, cursor, limit);
        CursorResponse<ReviewResponse> response = getReviewUseCase.getCursorPage(request);
        return ResponseEntity.ok(response);
    }

}
