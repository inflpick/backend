package com.leesh.inflpick.review.adapter.in.web.controller;

import com.leesh.inflpick.review.adapter.out.docs.swagger.UpdateReviewControllerDocs;
import com.leesh.inflpick.review.application.dto.ReviewRequest;
import com.leesh.inflpick.review.application.port.in.UpdateReviewUseCase;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UpdateReviewController implements UpdateReviewControllerDocs {

    private final UpdateReviewUseCase updateReviewUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/reviews/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") String id,
                                       @RequestBody ReviewRequest request) {
        ReviewId reviewId = ReviewId.create(id);
        updateReviewUseCase.update(reviewId, request);
        return ResponseEntity.noContent().build();
    }

}
