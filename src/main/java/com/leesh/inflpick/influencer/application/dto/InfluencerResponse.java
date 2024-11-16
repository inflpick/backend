package com.leesh.inflpick.influencer.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.influencer.adapter.out.docs.swagger.dto.InfluencerResponseDocs;
import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.domain.Keyword;

import java.time.Instant;
import java.util.List;

public record InfluencerResponse(String id,
                                 String name,
                                 String introduction,
                                 String profileImageUrl,
                                 List<SnsProfileLinkResponse> socialMediaProfileLinks,
                                 List<KeywordResponse> keywords,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                 Instant createdDate,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                 Instant lastModifiedDate) implements InfluencerResponseDocs {

    public static InfluencerResponse create(Influencer influencer, List<Keyword> keywords, String profileImageUrl) {

        List<SnsProfileLinkResponse> snsProfileLinkResponse = influencer.snsProfileLinks().links().stream()
                .map(SnsProfileLinkResponse::create)
                .toList();

        List<KeywordResponse> keywordResponses = keywords.stream()
                .map(KeywordResponse::create)
                .toList();

        return new InfluencerResponse(influencer.id().id(),
                influencer.name().name(),
                influencer.introduction().introduction(),
                profileImageUrl,
                snsProfileLinkResponse,
                keywordResponses,
                influencer.createdDate(),
                influencer.lastModifiedDate());
    }
}
