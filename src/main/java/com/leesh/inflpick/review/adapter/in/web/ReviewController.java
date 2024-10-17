package com.leesh.inflpick.review.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.CursorResponse;
import com.leesh.inflpick.common.port.CursorPage;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.port.in.InfluencerQueryService;
import com.leesh.inflpick.product.adapter.in.web.value.ProductWebResponse;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.review.adapter.in.web.docs.ReviewApiDocs;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCommand;
import com.leesh.inflpick.review.port.ReviewCursorQuery;
import com.leesh.inflpick.review.port.in.ReviewCommandService;
import com.leesh.inflpick.review.port.in.ReviewQueryService;
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

@Builder
@RequiredArgsConstructor
@RequestMapping(path = "/reviews")
@RestController
public class ReviewController implements ReviewApiDocs {

    private final InfluencerQueryService influencerQueryService;
    private final ProductQueryService productQueryService;
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;
    private final StorageService storageService;

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
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
