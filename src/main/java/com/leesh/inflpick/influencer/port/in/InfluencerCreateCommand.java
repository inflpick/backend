package com.leesh.inflpick.influencer.port.in;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.influencer.core.domain.value.*;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public record InfluencerCreateCommand(@NotNull InfluencerName name,
                                      @NotNull InfluencerIntroduction briefIntroduction,
                                      @NotNull InfluencerDescription description,
                                      @NotNull ProfileImage profileImage,
                                      @NotNull Set<String> keywordUuids,
                                      @NotNull SocialMediaProfileLinks socialMediaProfileLinks) {

    public Influencer toEntity(UuidHolder uuidHolder) {
        return Influencer.builder()
                .uuid(uuidHolder.uuid())
                .name(name)
                .introduction(briefIntroduction)
                .description(description)
                .profileImage(profileImage)
                .keywords(Keywords.EMPTY)
                .socialMediaProfileLinks(socialMediaProfileLinks)
                .build();
    }
}
