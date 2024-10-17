package com.leesh.inflpick.review.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.review.adapter.in.web.docs.ReviewRequestApiDocs;
import com.leesh.inflpick.review.core.domain.ReviewSource;
import com.leesh.inflpick.review.port.ReviewCommand;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

@Builder
public record ReviewRequest(
        String influencerId,
        String productId,
        String reviewContents,
        String reviewSourceUri,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant reviewedDate) implements ReviewRequestApiDocs {

    public ReviewRequest(@Nullable String influencerId,
                         @Nullable String productId,
                         @Nullable String reviewContents,
                         @Nullable String reviewSourceUri,
                         @Nullable Instant reviewedDate) {
        RequiredFieldsValidator.validate(influencerId, productId, reviewContents, reviewSourceUri);
        assert influencerId != null;
        this.influencerId = influencerId.strip();
        assert productId != null;
        this.productId = productId.strip();
        assert reviewContents != null;
        this.reviewContents = reviewContents.strip();
        assert reviewSourceUri != null;
        this.reviewSourceUri = reviewSourceUri.strip();
        this.reviewedDate = Objects.requireNonNullElse(reviewedDate, Instant.MIN);
    }

    public ReviewCommand toCommand() {
        RequiredFieldsValidator.validate(influencerId, productId, reviewContents, reviewSourceUri);
        ReviewSource reviewSource = ReviewSource.of(reviewContents, reviewSourceUri, reviewedDate);
        return ReviewCommand.of(reviewSource);
    }

}
