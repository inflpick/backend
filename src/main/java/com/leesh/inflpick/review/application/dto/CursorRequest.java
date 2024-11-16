package com.leesh.inflpick.review.application.dto;

import com.leesh.inflpick.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.product.domain.vo.ProductId;

import java.time.Instant;

public record CursorRequest(InfluencerId influencerId,
                            ProductId productId,
                            Instant cursor,
                            Integer limit) {
}
