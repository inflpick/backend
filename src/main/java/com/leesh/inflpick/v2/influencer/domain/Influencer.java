package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Builder(access = AccessLevel.PUBLIC, builderMethodName = "requiredBuilder")
public final class Influencer {

    @Builder.Default
    @Getter
    private final InfluencerId id = InfluencerId.empty();
    @Getter
    private InfluencerName name;
    @Builder.Default
    @Getter
    private InfluencerIntroduction introduction = InfluencerIntroduction.empty();
    @Builder.Default
    @Getter
    private InfluencerDescription description = InfluencerDescription.empty();
    @Builder.Default
    @Getter
    private ProfileImage profileImage = ProfileImage.empty();
    @Builder.Default
    private InfluencerKeywordIds keywordIds = InfluencerKeywordIds.empty();
    @Builder.Default
    @Getter
    private SnsProfileLinks snsProfileLinks = SnsProfileLinks.empty();
    @Builder.Default
    private final Instant createdDate = Instant.MIN;
    @Builder.Default
    private final String createdBy = "";
    @Builder.Default
    private final Instant lastModifiedDate = Instant.MIN;
    @Builder.Default
    private final String lastModifiedBy = "";

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

    /* Business Logic */
    public void addKeywordIds(Set<KeywordId> keywordIds) {
        this.keywordIds = this.keywordIds.addAll(keywordIds);
    }

    public Set<KeywordId> getKeywordIds() {
        return this.keywordIds.getValues();
    }

    public void addSnsProfileLinks(Set<SnsProfileLink> snsProfileLinks) {
        this.snsProfileLinks = this.snsProfileLinks.addAll(snsProfileLinks);
    }

    public void update(InfluencerName name,
                       InfluencerIntroduction introduction,
                       InfluencerDescription description,
                       Set<KeywordId> keywordIds,
                       Set<SnsProfileLink> snsProfileLinks) {
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.keywordIds = InfluencerKeywordIds.create(keywordIds);
        this.snsProfileLinks = SnsProfileLinks.create(snsProfileLinks);
    }

    public void updateProfileImage(String profileImagePath) {
        this.profileImage = ProfileImage.create(profileImagePath);
    }
}
