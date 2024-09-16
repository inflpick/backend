package com.leesh.inflpick.influencer.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

public class Influencer {

    private final InfluencerId id;
    private final InfluencerName name;
    private final InfluencerDescription description;
    private final ProfileImage profileImage;
    @Getter
    private final SocialMediaLinks socialMediaLinks;

    @Builder
    public Influencer(InfluencerId id,
                      InfluencerName name,
                      InfluencerDescription description,
                      ProfileImage profileImage,
                      SocialMediaLinks socialMediaLinks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.profileImage = profileImage;
        this.socialMediaLinks = socialMediaLinks;
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

    public String getId() {
        return id.id();
    }

    public String getName() {
        return name.name();
    }

    public String getDescription() {
        return description.description();
    }

    public String getProfileImage() {
        return profileImage.uri().toASCIIString();
    }
}
