package com.leesh.inflpick.influencer.port;

import com.leesh.inflpick.v2.shared.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerDescription;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerIntroduction;
import com.leesh.inflpick.v2.influencer.domain.vo.InfluencerName;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record InfluencerCommand(@NotNull InfluencerName name,
                                @NotNull InfluencerIntroduction introduction,
                                @NotNull InfluencerDescription description,
                                @NotNull Set<String> keywordIds,
                                @NotNull SocialMediaProfileLinks socialMediaProfileLinks) {

    public Influencer toEntity(UuidHolder uuidHolder) {
        return Influencer.builder()
                .id(uuidHolder.uuid())
                .name(name)
                .introduction(introduction)
                .description(description)
                .socialMediaProfileLinks(socialMediaProfileLinks)
                .build();
    }
}
