package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.*;
import org.jetbrains.annotations.NotNull;

public record InfluencerCreateCommand(@NotNull InfluencerName name,
                                      @NotNull InfluencerIntroduction briefIntroduction,
                                      @NotNull InfluencerDescription description,
                                      @NotNull ProfileImage profileImage,
                                      @NotNull SocialMediaProfileLinks socialMediaProfileLinks) {

    public Influencer toEntity(UuidHolder uuidHolder) {
        return Influencer.builder()
                .uuid(uuidHolder.uuid())
                .name(name)
                .introduction(briefIntroduction)
                .description(description)
                .profileImage(profileImage)
                .socialMediaProfileLinks(socialMediaProfileLinks)
                .build();
    }
}
