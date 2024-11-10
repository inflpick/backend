package com.leesh.inflpick.v2.review.application.dto;

import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerId;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

import java.time.Instant;

public record CursorRequest(InfluencerId influencerId,
                            ProductId productId,
                            Instant cursor,
                            Integer limit) {
}
