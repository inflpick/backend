package com.leesh.inflpick.influencer.application.dto;

import com.leesh.inflpick.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.adapter.out.docs.swagger.dto.InfluencerRequestDocs;
import com.leesh.inflpick.influencer.domain.Influencer;
import com.leesh.inflpick.influencer.domain.SnsProfileLinks;
import com.leesh.inflpick.influencer.domain.vo.SnsProfileLink;

import java.util.List;

public record InfluencerRequest(String name,
                                String introduction,
                                String description,
                                List<String> keywordIds,
                                List<SnsProfileLinkRequest> socialMediaProfileLinks) implements InfluencerRequestDocs {

    public InfluencerRequest {
        RequiredFieldsValidator.validate(name, introduction, description);
        keywordIds = (keywordIds == null) ? List.of() : keywordIds;
        socialMediaProfileLinks = (socialMediaProfileLinks == null) ? List.of() : socialMediaProfileLinks;
    }

    public Influencer toEntity() {
        List<SnsProfileLink> profileLinks = socialMediaProfileLinks.stream()
                .map(SnsProfileLinkRequest::toEntity)
                .toList();
        SnsProfileLinks snsProfileLinks = SnsProfileLinks.create(profileLinks);
        return Influencer.withoutId(name, introduction, description, snsProfileLinks);
    }
}
