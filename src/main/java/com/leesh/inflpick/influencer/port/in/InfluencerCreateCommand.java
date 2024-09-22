package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerDescription;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerIntroduction;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerName;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record InfluencerCreateCommand(@NotNull InfluencerName name,
                                      @NotNull InfluencerIntroduction briefIntroduction,
                                      @NotNull InfluencerDescription description,
                                      @NotNull Set<String> keywordUuids,
                                      @NotNull SocialMediaProfileLinks socialMediaProfileLinks) {

    public Influencer toEntity(UuidHolder uuidHolder) {
        return Influencer.builder()
                .uuid(uuidHolder.uuid())
                .name(name)
                .introduction(briefIntroduction)
                .description(description)
                .socialMediaProfileLinks(socialMediaProfileLinks)
                .build();
    }
}
