package com.leesh.inflpick.v2.application.dto.influencer;

import com.leesh.inflpick.v2.domain.influencer.Influencer;
import com.leesh.inflpick.v2.domain.influencer.vo.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;

@Getter
@Builder(builderMethodName = "requiredBuilder")
public final class InfluencerCommand {

    private final InfluencerName name;
    @Builder.Default
    private final InfluencerIntroduction introduction = InfluencerIntroduction.empty();
    @Builder.Default
    private final InfluencerDescription description = InfluencerDescription.empty();
    @Builder.Default
    private final Set<InfluencerKeywordId> keywordIds = Set.of();
    @Builder.Default
    private final Set<SnsProfileLink> snsProfileLinks = Set.of();

    public static InfluencerCommandBuilder builder(InfluencerName name) {
        return requiredBuilder()
                .name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfluencerCommand that = (InfluencerCommand) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(introduction, that.introduction) &&
                Objects.equals(description, that.description) &&
                Objects.equals(keywordIds, that.keywordIds) &&
                Objects.equals(snsProfileLinks, that.snsProfileLinks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, introduction, description, keywordIds, snsProfileLinks);
    }

    public Influencer toEntity() {
        Influencer influencer = Influencer.builder(name)
                .introduction(introduction)
                .description(description)
                .build();
        return influencer;
    }
}
