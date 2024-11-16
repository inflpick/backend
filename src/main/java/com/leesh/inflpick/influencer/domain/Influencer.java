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
                description,
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
        InfluencerDescription influencerDescription = InfluencerDescription.create(request.description());
        List<SnsProfileLink> profileLinks = request.socialMediaProfileLinks().stream()
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
