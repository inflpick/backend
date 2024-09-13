package com.leesh.inflpick.influencer.domain;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public class Influencer {

    private final @NotNull InfluencerId id;
    private final @NotNull Profile profile;
    private final @NotNull SocialMediaLinks socialMediaLinks;
    private final @NotNull Instant createdDate;
    private final @NotNull String createdBy;
    private final @NotNull Instant lastModifiedDate;
    private final @NotNull String lastModifiedBy;

    @Builder
    public Influencer(@NotNull InfluencerId id,
                      Profile profile,
                      SocialMediaLinks socialMediaLinks,
                      Instant createdDate,
                      String createdBy,
                      Instant lastModifiedDate,
                      String lastModifiedBy) {

        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(profile, "profile must not be null");
        Objects.requireNonNull(socialMediaLinks, "socialMediaLinks must not be null");
        Objects.requireNonNull(createdDate, "createdDate must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
        Objects.requireNonNull(lastModifiedDate, "lastModifiedDate must not be null");
        Objects.requireNonNull(lastModifiedBy, "lastModifiedBy must not be null");

        this.id = id;
        this.profile = profile;
        this.socialMediaLinks = socialMediaLinks;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedBy = lastModifiedBy;
    }
}
