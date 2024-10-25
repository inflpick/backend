package com.leesh.inflpick.v2.influencer.application.dto;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerDescription;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerIntroduction;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerName;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;

import java.util.Set;

public record InfluencerCommand(InfluencerName name,
                                InfluencerIntroduction introduction,
                                InfluencerDescription description,
                                Set<KeywordId> keywordIds,
                                Set<SnsProfileLink> snsProfileLinks) {

    public Influencer toEntity() {
        Influencer influencer = Influencer.builder(name)
                .introduction(introduction)
                .description(description)
                .build();
        influencer.addKeywordIds(keywordIds);
        influencer.addSnsProfileLinks(snsProfileLinks);
        return influencer;
    }
}
