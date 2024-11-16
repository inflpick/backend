package com.leesh.inflpick.review.adapter.in.web.controller;

import com.leesh.inflpick.review.adapter.out.docs.swagger.DeleteReviewControllerDocs;
import com.leesh.inflpick.review.application.port.in.DeleteReviewUseCase;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteReviewController implements DeleteReviewControllerDocs {

    private final DeleteReviewUseCase deleteReviewUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        ReviewId reviewId = ReviewId.create(id);
        deleteReviewUseCase.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}
