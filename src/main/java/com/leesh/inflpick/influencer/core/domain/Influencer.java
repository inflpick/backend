package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.InfluencerIntroduction;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.core.domain.value.ProfileImage;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.review.core.domain.Review;
import com.leesh.inflpick.review.port.ReviewCommand;
import com.leesh.inflpick.v2.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerDescription;
import com.leesh.inflpick.v2.domain.influencer.vo.InfluencerName;
import lombok.Builder;
import lombok.Getter;

import java.nio.file.Path;
import java.time.Instant;
import java.util.Objects;

public class Influencer {

    @Getter
    private final String id;
    private InfluencerName name;
    private InfluencerDescription description;
    private InfluencerIntroduction introduction;
    private ProfileImage profileImage;
    @Getter
    private SocialMediaProfileLinks socialMediaProfileLinks;
    @Getter
    private Keywords keywords;
    @Getter
    private final String createdBy;
    @Getter
    private final Instant createdDate;
    @Getter
    private final String lastModifiedBy;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Influencer(String id,
                      InfluencerName name,
                      InfluencerDescription description,
                      InfluencerIntroduction introduction,
                      ProfileImage profileImage,
                      SocialMediaProfileLinks socialMediaProfileLinks,
                      Keywords keywords, String createdBy,
                      Instant createdDate, String lastModifiedBy,
                      Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.introduction = introduction;
        this.profileImage = profileImage == null ? ProfileImage.EMPTY : profileImage;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
        this.keywords = keywords == null ? Keywords.EMPTY : keywords;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
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

    public String getName() {
        return name.name();
    }

    public String getDescription() {
        return description.description();
    }

    public String getIntroduction() {
        return introduction.introduction();
    }

    public String getProfileImagePath() {
        return profileImage.imagePath();
    }

    public void addKeywords(Keywords keywords) {
        this.keywords.addAll(keywords);
    }

    public Path getProfileImageBasePath() {
        return profileImage.basePath(this.id);
    }

    public void updateProfileImagePath(String imagePath) {
        this.profileImage = ProfileImage.from(imagePath);
    }

    public void update(InfluencerName name,
                       InfluencerIntroduction introduction,
                       InfluencerDescription description,
                       Keywords keywords,
                       SocialMediaProfileLinks socialMediaProfileLinks) {
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.keywords = keywords;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
    }

    public Review review(Product product, ReviewCommand command, UuidHolder uuidHolder) {
        return Review.builder()
                .id(uuidHolder.uuid())
                .reviewer(this)
                .product(product)
                .source(command.reviewSource())
                .build();
    }
}
