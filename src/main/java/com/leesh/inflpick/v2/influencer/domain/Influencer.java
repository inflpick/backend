package com.leesh.inflpick.v2.influencer.domain;

import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import com.leesh.inflpick.v2.influencer.application.dto.SnsProfileLinkRequest;
import com.leesh.inflpick.v2.influencer.domain.exception.MaximumInfluencerKeywordSizeException;
import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.review.domain.Review;
import com.leesh.inflpick.v2.review.domain.vo.ReviewSource;

import java.time.Instant;
import java.util.List;

public record Influencer(InfluencerId id,
                         InfluencerName name,
                         InfluencerIntroduction introduction,
                         InfluencerDescription description,
                         ProfileImage profileImage,
                         SnsProfileLinks snsProfileLinks,
                         Keywords keywords,
                         Instant createdDate,
                         String createdBy,
                         Instant lastModifiedDate,
                         String lastModifiedBy) {

    /* Business Logic */
    public static Influencer withId(InfluencerId id,
                                    InfluencerName name,
                                    InfluencerIntroduction introduction,
                                    InfluencerDescription description,
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
                description,
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
                                       String description,
                                       SnsProfileLinks snsProfileLinks) {
        InfluencerId id = InfluencerId.empty();
        ProfileImage profileImage = ProfileImage.empty();
        InfluencerName influencerName = InfluencerName.create(name);
        InfluencerIntroduction influencerIntroduction = InfluencerIntroduction.create(introduction);
        InfluencerDescription influencerDescription = InfluencerDescription.create(description);
        Keywords keywords = Keywords.empty();
        return new Influencer(id,
                influencerName,
                influencerIntroduction,
                influencerDescription,
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
                description,
                ProfileImage.create(profileImagePath),
                snsProfileLinks,
                keywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }

    public Influencer addKeywords(List<Keyword> keywords) {
        if (this.keywords.size() + keywords.size() > 10) {
            throw new MaximumInfluencerKeywordSizeException(this.keywords.size());
        }
        Keywords addedKeywords = this.keywords.add(keywords);
        return withId(id,
                name,
                introduction,
                description,
                profileImage,
                snsProfileLinks,
                addedKeywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }

    public Review review(ProductId productId, ReviewSource source) {
        return Review.withoutPersistence(source, id, productId);
    }

    public Influencer update(InfluencerRequest command) {
        InfluencerName influencerName = InfluencerName.create(command.name());
        InfluencerIntroduction influencerIntroduction = InfluencerIntroduction.create(command.introduction());
        InfluencerDescription influencerDescription = InfluencerDescription.create(command.description());
        List<SnsProfileLink> profileLinks = command.socialMediaProfileLinks().stream()
                .map(SnsProfileLinkRequest::toEntity)
                .toList();
        SnsProfileLinks links = SnsProfileLinks.create(profileLinks);
        return withId(id,
                influencerName,
                influencerIntroduction,
                influencerDescription,
                profileImage,
                links,
                keywords,
                createdDate,
                createdBy,
                Instant.now(),
                lastModifiedBy);
    }
}
