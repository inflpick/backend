package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collection = "influencers")
public class InfluencerDocument {

    @Getter
    private final String uuid;
    private final String name;
    private final String introduction;
    private final String description;
    private final String profileImagePath;
    @Getter
    private final Set<String> keywordUuids;
    private final Set<SocialMediaProfileLink> socialMediaProfileLinks;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;
    @Version
    private final Long version;

    @Builder(access = AccessLevel.PRIVATE)
    private InfluencerDocument(String uuid,
                               String name,
                               String introduction,
                               String description,
                               String profileImagePath,
                               Set<String> keywordUuids,
                               Set<SocialMediaProfileLink> socialMediaProfileLinks,
                               String createdBy,
                               Instant createdDate,
                               String lastModifiedBy,
                               Instant lastModifiedDate, Long version) {
        this.uuid = uuid;
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.profileImagePath = profileImagePath;
        this.keywordUuids = keywordUuids;
        this.socialMediaProfileLinks = socialMediaProfileLinks;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
        this.version = version;
    }

    public static InfluencerDocument from(Influencer influencer) {

        Set<String> keywordUuids = influencer.getKeywords()
                .getUuids();

        return InfluencerDocument.builder()
                .uuid(influencer.getUuid())
                .name(influencer.getName())
                .introduction(influencer.getIntroduction())
                .description(influencer.getDescription())
                .profileImagePath(influencer.getProfileImage())
                .keywordUuids(keywordUuids)
                .socialMediaProfileLinks(influencer.getSocialMediaProfileLinks().getImmutable())
                .build();
    }

    public Influencer toEntity(Keywords keywords) {

        return Influencer.builder()
                .uuid(uuid)
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
}
