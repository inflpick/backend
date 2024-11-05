package com.leesh.inflpick.v2.review.adapter.in;

import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import com.leesh.inflpick.v2.review.application.port.in.UpdateReviewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UpdateReviewController {

    private final UpdateReviewUseCase updateReviewUseCase;

    @PutMapping("/reviews")
    public ResponseEntity<Void> update(@RequestBody ReviewRequest request) {
        updateReviewUseCase.update(null, null);
        return ResponseEntity.noContent().build();
    }

}
