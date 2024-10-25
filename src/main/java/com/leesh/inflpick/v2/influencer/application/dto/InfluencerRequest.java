package com.leesh.inflpick.v2.influencer.application.dto;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerDescription;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerIntroduction;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerName;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

import java.util.List;

public record InfluencerRequest(InfluencerName name,
                                InfluencerIntroduction introduction,
                                InfluencerDescription description,
                                List<KeywordId> keywordIds,
                                List<SnsProfileLink> snsProfileLinks) {

    public Influencer toEntity() {
        return Influencer.builder(name)
                .introduction(introduction)
                .description(description)
                .build();
    }
}
