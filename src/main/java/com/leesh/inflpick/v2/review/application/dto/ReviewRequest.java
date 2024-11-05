package com.leesh.inflpick.v2.review.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public record ReviewRequest(String influencerId,
                            String productId,
                            String contents,
                            String url,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                            Instant reviewDate) {
}
