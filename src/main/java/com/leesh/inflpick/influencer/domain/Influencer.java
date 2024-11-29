package com.leesh.inflpick.influencer.domain;

import com.leesh.inflpick.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.influencer.application.dto.SnsProfileLinkRequest;
import com.leesh.inflpick.influencer.domain.exception.MaximumInfluencerKeywordSizeException;
import com.leesh.inflpick.influencer.domain.vo.*;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.Keywords;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.product.domain.vo.ProductId;
import com.leesh.inflpick.review.domain.Review;
import com.leesh.inflpick.review.domain.vo.ReviewSource;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class Influencer {
    private final InfluencerId id;
    private final InfluencerName name;
    private final InfluencerIntroduction introduction;
    private final ProfileImage profileImage;
    private final SnsProfileLinks snsProfileLinks;
    private final Keywords keywords;
    private final Instant createdDate;
    private final String createdBy;
    private final Instant lastModifiedDate;
    private final String lastModifiedBy;

    public Influencer(InfluencerId id,
                      InfluencerName name,
                      InfluencerIntroduction introduction,
                      ProfileImage profileImage,
                      SnsProfileLinks snsProfileLinks,
                      Keywords keywords,
                      Instant createdDate,
                      String createdBy,
                      Instant lastModifiedDate,
                      String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.profileImage = profileImage;
        this.snsProfileLinks = snsProfileLinks;
        this.keywords = keywords;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }

    /* Business Logic */
    public static Influencer withId(InfluencerId id,
                                    InfluencerName name,
                                    InfluencerIntroduction introduction,
                                    ProfileImage profileImage,
                                    SnsProfileLinks snsProfileLinks,
                                    Keywords keywords,
                                    Instant createdDate,
                                    String createdBy,
                                    Instant lastModifiedDate,
                                    String lastModifiedBy) {
        return new Influencer(id,
                name,
                introduction,
                profileImage,
                snsProfileLinks,
                keywords,
                createdDate,
                createdBy,
                lastModifiedDate,
                lastModifiedBy);
    }

    public static Influencer withoutId(String name,
                                       String introduction,
                                       SnsProfileLinks snsProfileLinks) {
        InfluencerId id = InfluencerId.empty();
        ProfileImage profileImage = ProfileImage.empty();
        InfluencerName influencerName = InfluencerName.create(name);
        InfluencerIntroduction influencerIntroduction = InfluencerIntroduction.create(introduction);
        Keywords keywords = Keywords.empty();
        return new Influencer(id,
                influencerName,
                influencerIntroduction,
                profileImage,
                snsProfileLinks,
                keywords,
                null,
                null,
                null,
                null);
    }

    public Influencer updateProfileImage(String profileImagePath) {
        return withId(id,
                name,
                introduction,
                ProfileImage.create(profileImagePath),
                snsProfileLinks,
                keywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }

    public Influencer putKeywords(List<Keyword> keywords) {
        if (keywords.size() > 10) {
            throw new MaximumInfluencerKeywordSizeException(this.keywords.size());
        }
        List<KeywordId> keywordIds = keywords.stream()
                .map(Keyword::id)
                .toList();
        Keywords newKeywords = Keywords.create(keywordIds);
        return withId(id,
                name,
                introduction,
                profileImage,
                snsProfileLinks,
                newKeywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }

    public Review review(ProductId productId, ReviewSource source) {
        return Review.withoutId(source, id, productId);
    }

    public Influencer update(InfluencerRequest request) {
        InfluencerName influencerName = InfluencerName.create(request.name());
        InfluencerIntroduction influencerIntroduction = InfluencerIntroduction.create(request.introduction());
        List<SnsProfileLink> profileLinks = request.socialMediaProfileLinks().stream()
                .map(SnsProfileLinkRequest::toEntity)
                .toList();
        SnsProfileLinks links = SnsProfileLinks.create(profileLinks);
        return withId(id,
                influencerName,
                influencerIntroduction,
                profileImage,
                links,
                keywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }

    public InfluencerId id() {
        return id;
    }

    public InfluencerName name() {
        return name;
    }

    public InfluencerIntroduction introduction() {
        return introduction;
    }

    public ProfileImage profileImage() {
        return profileImage;
    }

    public SnsProfileLinks snsProfileLinks() {
        return snsProfileLinks;
    }

    public Keywords keywords() {
        return keywords;
    }

    public Instant createdDate() {
        return createdDate;
    }

    public String createdBy() {
        return createdBy;
    }

    public Instant lastModifiedDate() {
        return lastModifiedDate;
    }

    public String lastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Influencer) obj;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
