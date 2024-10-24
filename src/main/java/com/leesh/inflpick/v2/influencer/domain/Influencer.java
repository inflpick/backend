package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Builder(access = AccessLevel.PUBLIC, builderMethodName = "requiredBuilder")
@Getter
public final class Influencer {

    @Builder.Default
    private final InfluencerId id = InfluencerId.empty();
    private final InfluencerName name;
    @Builder.Default
    private final InfluencerIntroduction introduction = InfluencerIntroduction.empty();
    @Builder.Default
    private final InfluencerDescription description = InfluencerDescription.empty();
    @Builder.Default
    private final ProfileImage profileImage = ProfileImage.empty();
    @Builder.Default
    private final InfluencerKeywords influencerKeywords = InfluencerKeywords.empty();
    @Builder.Default
    private final SnsProfileLinks snsProfileLinks = SnsProfileLinks.empty();

    public static InfluencerBuilder builder(InfluencerName name) {
        return requiredBuilder()
                .name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influencer that = (Influencer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
