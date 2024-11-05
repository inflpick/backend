package com.leesh.inflpick.v2.review.adapter.in;

import com.leesh.inflpick.v2.review.application.dto.ReviewRequest;
import com.leesh.inflpick.v2.review.application.port.in.CreateReviewUseCase;
import com.leesh.inflpick.v2.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CreateReviewController {

    private final CreateReviewUseCase createReviewUseCase;

    @PostMapping("/reviews")
    public ResponseEntity<Void> create(@RequestBody ReviewRequest request) {
        ReviewId reviewId = createReviewUseCase.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(reviewId)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
