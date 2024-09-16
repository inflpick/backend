package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.*;
import org.jetbrains.annotations.NotNull;

public record InfluencerCreateCommand(@NotNull InfluencerName name,
                                      @NotNull InfluencerDescription description,
                                      @NotNull ProfileImage profileImage,
                                      @NotNull SocialMediaLinks socialMediaLinks) {

    public Influencer toEntity(UuidHolder uuidHolder) {
        InfluencerId id = new InfluencerId(uuidHolder.uuid());
        return Influencer.builder()
                .id(id)
                .name(name)
                .description(description)
                .profileImage(profileImage)
                .socialMediaLinks(socialMediaLinks)
                .build();
    }
}
