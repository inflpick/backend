package com.leesh.inflpick.v2.influencer.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.influencer.adapter.in.web.docs.InfluencerWebResponseDocs;
import com.leesh.inflpick.v2.influencer.application.dto.QueryInfluencerResponse;
import com.leesh.inflpick.v2.influencer.domain.Influencer;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record QueryInfluencerWebResponse(String id,
                                         String name,
                                         String introduction,
                                         String description,
                                         String profileImageUrl,
                                         List<SnsProfileLinkWebResponse> socialMediaProfileLinks,
                                         List<KeywordWebResponse> keywords,
                                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                         Instant createdDate,
                                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                         Instant lastModifiedDate) implements InfluencerWebResponseDocs {

    public static QueryInfluencerWebResponse from(QueryInfluencerResponse queryInfluencerResponse) {

        Influencer influencer = queryInfluencerResponse.influencer();
        List<SnsProfileLinkWebResponse> snsProfileLinkWebResponse = influencer.getSnsProfileLinks().getLinks().stream()
                .map(SnsProfileLinkWebResponse::from)
                .toList();

        List<KeywordWebResponse> keywordResponses = queryInfluencerResponse.influencerKeywords().stream()
                .map(KeywordWebResponse::from)
                .toList();

        return QueryInfluencerWebResponse.builder()
                .id(influencer.getId().getValue())
                .name(influencer.getName().getValue())
                .introduction(influencer.getIntroduction().getValue())
                .description(influencer.getDescription().getValue())
                .profileImageUrl(queryInfluencerResponse.profileImageUrl())
                .socialMediaProfileLinks(snsProfileLinkWebResponse)
                .keywords(keywordResponses)
                .createdDate(influencer.getCreatedDate())
                .lastModifiedDate(influencer.getLastModifiedDate())
                .build();
    }
}
