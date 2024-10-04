package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collection = "influencers")
public class InfluencerDocument implements Persistable<String> {
    @Id
    @Getter
    private final String id;
    private final String name;
    private final String introduction;
    private final String description;
    private final String profileImagePath;
    @Getter
    private final Set<String> keywordIds;
    private final Set<SocialMediaProfileLink> socialMediaProfileLinks;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    @Builder(access = AccessLevel.PRIVATE)
    private InfluencerDocument(String id,
                               String name,
                               String introduction,
                               String description,
                               String profileImagePath,
                               Set<String> keywordIds,
                               Set<SocialMediaProfileLink> socialMediaProfileLinks,
                               String createdBy,
                               Instant createdDate,
                               String lastModifiedBy,
                               Instant lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.profileImagePath = profileImagePath;
        this.keywordIds = keywordIds;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static InfluencerDocument from(Influencer influencer) {

        Set<String> keywordIds = influencer.getKeywords()
                .getIds();

        return InfluencerDocument.builder()
                .id(influencer.getId())
                .name(influencer.getName())
                .introduction(influencer.getIntroduction())
                .description(influencer.getDescription())
                .profileImagePath(influencer.getProfileImage())
                .keywordIds(keywordIds)
                .socialMediaProfileLinks(influencer.getSocialMediaProfileLinks().getImmutable())
                .createdDate(influencer.getCreatedDate())
                .lastModifiedDate(influencer.getLastModifiedDate())
                .build();
    }

    public Influencer toEntity(Keywords keywords) {

        return Influencer.builder()
                .id(id)
                .name(InfluencerName.from(name))
                .introduction(InfluencerIntroduction.from(introduction))
                .description(InfluencerDescription.from(description))
                .profileImage(ProfileImage.from(profileImagePath))
                .keywords(keywords)
                .socialMediaProfileLinks(SocialMediaProfileLinks.from(socialMediaProfileLinks))
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
