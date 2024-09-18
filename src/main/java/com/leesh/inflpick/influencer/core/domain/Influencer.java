package com.leesh.inflpick.influencer.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

public class Influencer {

    @Getter
    private final String uuid;
    private final InfluencerName name;
    private final InfluencerDescription description;
    private final InfluencerIntroduction introduction;
    private final ProfileImage profileImage;
    @Getter
    private final SocialMediaProfileLinks socialMediaProfileLinks;
    @Getter
    private final Keywords keywords;
    @Getter
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Influencer(String uuid,
                      InfluencerName name,
                      InfluencerDescription description,
                      InfluencerIntroduction introduction,
                      ProfileImage profileImage,
                      SocialMediaProfileLinks socialMediaProfileLinks,
                      Keywords keywords, Instant createdDate, Instant lastModifiedDate) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.introduction = introduction;
        this.profileImage = profileImage;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
        this.keywords = keywords;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influencer that = (Influencer) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
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

    public String getProfileImage() {
        return profileImage.uri().toASCIIString();
    }
}
