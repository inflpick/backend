package com.leesh.inflpick.influencer.core.domain;

import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.core.service.InfluencerCommand;
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
    private final Instant createdDate;
    @Getter
    private final Instant lastModifiedDate;

    @Builder
    public Influencer(String id,
                      InfluencerName name,
                      InfluencerDescription description,
                      InfluencerIntroduction introduction,
                      ProfileImage profileImage,
                      SocialMediaProfileLinks socialMediaProfileLinks,
                      Keywords keywords,
                      Instant createdDate,
                      Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.introduction = introduction;
        this.profileImage = profileImage == null ? ProfileImage.EMPTY : profileImage;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
        this.keywords = keywords == null ? Keywords.EMPTY : keywords;
        this.createdDate = createdDate;
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

    public String getProfileImage() {
        return profileImage.imagePath();
    }

    public void addKeywords(Keywords keywords) {
        this.keywords.addAll(keywords);
    }

    public Path getProfileImageBasePath() {
        return profileImage.basePath(this.id);
    }

    public void registerProfileImage(String uploadPath) {
        this.profileImage = ProfileImage.from(uploadPath);
    }

    public void update(InfluencerCommand command, Keywords keywords) {
        this.name = command.name();
        this.introduction = command.introduction();
        this.description = command.description();
        this.keywords = keywords;
        this.socialMediaProfileLinks = command.socialMediaProfileLinks();
    }
}
