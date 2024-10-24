package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.adapter.in.web.value.SocialMediaProfileRequest;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerCommand;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerDescription;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerIntroduction;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerName;
import com.leesh.inflpick.v2.influencer.domain.vo.SnsProfileLink;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.Builder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record InfluencerWebRequest(
        String name,
        String introduction,
        String description,
        List<String> keywordIds,
        List<SocialMediaProfileRequest> socialMediaProfileLinks) implements InfluencerWebRequestDocs {

    public InfluencerWebRequest(@Nullable String name,
                                @Nullable String introduction,
                                @Nullable String description,
                                @Nullable List<String> keywordIds,
                                @Nullable List<SocialMediaProfileRequest> socialMediaProfileLinks) {

        RequiredFieldsValidator.validate(name, introduction, description);
        assert name != null;
        this.name = name.strip();
        assert introduction != null;
        this.introduction = introduction.strip();
        assert description != null;
        this.description = description.strip();
        this.keywordIds = keywordIds == null ? new ArrayList<>() : keywordIds.stream().map(String::strip).toList();
        this.socialMediaProfileLinks = socialMediaProfileLinks == null ? new ArrayList<>() : socialMediaProfileLinks.stream()
                .map(socialMediaProfileRequest -> new SocialMediaProfileRequest(socialMediaProfileRequest.platform().strip(), socialMediaProfileRequest.uri().strip()))
                .toList();
    }

    public InfluencerCommand toCommand() {
        RequiredFieldsValidator.validate(name, introduction, description);
        InfluencerName influencerName = InfluencerName.create(name);
        InfluencerDescription influencerDescription = InfluencerDescription.create(description);
        InfluencerIntroduction influencerIntroduction = InfluencerIntroduction.create(introduction);
        Set<KeywordId> keywordIdEntities = keywordIds.stream()
                .map(KeywordId::create)
                .collect(Collectors.toSet());
        Set<SnsProfileLink> snsProfileLinks = convertToSocialMediaProfileEntity(socialMediaProfileLinks);
        return new InfluencerCommand(influencerName, influencerIntroduction, influencerDescription, keywordIdEntities, snsProfileLinks);
    }

    private Set<SnsProfileLink> convertToSocialMediaProfileEntity(List<SocialMediaProfileRequest> links) {
        return links
                .stream()
                .map(SocialMediaProfileRequest::toEntity)
                .collect(Collectors.toSet());
    }
}
