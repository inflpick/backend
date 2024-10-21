package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.common.v2.adapter.validator.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.adapter.in.web.docs.InfluencerWebRequestApiDocs;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerDescription;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerIntroduction;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerName;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record InfluencerWebRequest(
        String name,
        String introduction,
        String description,
        List<String> keywordIds,
        List<SocialMediaProfileRequest> socialMediaProfileLinks) implements InfluencerWebRequestApiDocs {

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

    public @NotNull InfluencerCommand toCommand() {
        RequiredFieldsValidator.validate(name, introduction, description);
        InfluencerName influencerName = new InfluencerName(name);
        InfluencerDescription influencerDescription = new InfluencerDescription(description);
        InfluencerIntroduction influencerIntroduction = new InfluencerIntroduction(introduction);

        return new InfluencerCommand(
            influencerName,
            influencerIntroduction,
            influencerDescription,
            new HashSet<>(keywordIds),
            convertToSocialMediaProfileEntity(socialMediaProfileLinks)
        );
    }

    private @NotNull SocialMediaProfileLinks convertToSocialMediaProfileEntity(@NotNull List<SocialMediaProfileRequest> links) {
        Set<SocialMediaProfileLink> list = links
                .stream()
                .map(SocialMediaProfileRequest::toEntity)
                .collect(Collectors.toSet());
        return new SocialMediaProfileLinks(list);
    }
}
