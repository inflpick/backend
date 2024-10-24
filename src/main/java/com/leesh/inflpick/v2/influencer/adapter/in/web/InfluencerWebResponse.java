package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.influencer.adapter.in.web.value.SocialMediaProfileLinkResponse;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.v2.keyword.adapter.in.web.KeywordResponse;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record InfluencerWebResponse(
        String id,
        String name,
        String introduction,
        String description,
        String profileImageUrl,
        List<SocialMediaProfileLinkResponse> socialMediaProfileLinks,
        List<KeywordResponse> keywords,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant createdDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant lastModifiedDate) implements InfluencerWebResponseDocs {

    public static InfluencerWebResponse from(Influencer influencer, String profileImageUrl) {

        List<SocialMediaProfileLinkResponse> socialMediaProfileLinkResponse = influencer.getSocialMediaProfileLinks().links().stream()
                .map(SocialMediaProfileLinkResponse::from)
                .toList();

        List<KeywordResponse> keywordResponses = influencer.getKeywords().keywords().stream()
                .map(KeywordResponse::from)
                .toList();

        return InfluencerWebResponse.builder()
                .id(influencer.getId())
                .name(influencer.getName())
                .introduction(influencer.getIntroduction())
                .description(influencer.getDescription())
                .profileImageUrl(profileImageUrl)
                .socialMediaProfileLinks(socialMediaProfileLinkResponse)
                .keywords(keywordResponses)
                .createdDate(influencer.getCreatedDate())
                .lastModifiedDate(influencer.getLastModifiedDate())
                .build();
    }
}
