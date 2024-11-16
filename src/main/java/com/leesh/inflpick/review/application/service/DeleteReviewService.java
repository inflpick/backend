package com.leesh.inflpick.review.application.service;

import com.leesh.inflpick.review.application.port.in.DeleteReviewUseCase;
import com.leesh.inflpick.review.application.port.out.CommandReviewPort;
import com.leesh.inflpick.review.domain.vo.ReviewId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteReviewService implements DeleteReviewUseCase {

    private final CommandReviewPort commandReviewPort;

    @Override
    public void delete(ReviewId id) {
        commandReviewPort.delete(id);
    }
}
