package com.leesh.inflpick.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.review.adapter.out.docs.swagger.ReviewRequestDocs;

import java.time.Instant;

public record ReviewRequest(String influencerId,
                            String productId,
                            String contents,
                            String url,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                            Instant reviewDate) implements ReviewRequestDocs {
}
