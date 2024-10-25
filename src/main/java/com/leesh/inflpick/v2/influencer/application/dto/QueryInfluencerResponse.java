package com.leesh.inflpick.v2.influencer.application.dto;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;

import java.util.List;

public record QueryInfluencerResponse(Influencer influencer,
                                      List<Keyword> influencerKeywords,
                                      String profileImageUrl) {
}
