package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.domain.exception.MaximumInfluencerKeywordSizeException;
import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.keyword.domain.vo.Keyword;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
@Builder(access = AccessLevel.PUBLIC, builderMethodName = "requiredBuilder")
public final class Influencer {

    @Builder.Default
    private final InfluencerId id = InfluencerId.empty();
    private InfluencerName name;
    @Builder.Default
    private InfluencerIntroduction introduction = InfluencerIntroduction.empty();
    @Builder.Default
    private InfluencerDescription description = InfluencerDescription.empty();
    @Builder.Default
    private ProfileImage profileImage = ProfileImage.empty();
    @Builder.Default
    private SnsProfileLinks snsProfileLinks = SnsProfileLinks.empty();
    @Builder.Default
    private Keywords keywords = Keywords.empty();
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
    public void update(InfluencerName name,
                       InfluencerIntroduction introduction,
                       InfluencerDescription description,
                       List<KeywordId> keywords,
                       List<SnsProfileLink> snsProfileLinks) {
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.keywords = Keywords.create(keywords);
        this.snsProfileLinks = SnsProfileLinks.create(snsProfileLinks);
    }

    public void updateProfileImage(String profileImagePath) {
        this.profileImage = ProfileImage.create(profileImagePath);
    }

    public void addKeywords(List<KeywordId> keywords) {
        if (keywords.size() > 10) {
            throw new MaximumInfluencerKeywordSizeException("Influencer Keyword size cannot exceed 10, current size: " + this.keywords.size());
        }
        this.keywords = Keywords.create(keywords);
    }

    public void addSnsProfileLinks(List<SnsProfileLink> links) {
        this.snsProfileLinks = SnsProfileLinks.create(links);
    }
}
