package com.leesh.inflpick.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.influencer.core.domain.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Document(collection = "influencers")
public class InfluencerDocument {

    @Getter
    private final String uuid;
    private final String name;
    private final String introduction;
    private final String description;
    private final String profileImageUri;
    @Getter
    private final Set<String> keywordUuids;
    private final List<SocialMediaProfileLink> socialMediaProfileLinks;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Instant createdDate;
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
                               String profileImageUri,
                               Set<String> keywordUuids,
                               List<SocialMediaProfileLink> socialMediaProfileLinks,
                               String createdBy,
                               Instant createdDate,
                               String lastModifiedBy,
                               Instant lastModifiedDate, Long version) {
        this.uuid = uuid;
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.profileImageUri = profileImageUri;
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
                .profileImageUri(influencer.getProfileImage())
                .keywordUuids(keywordUuids)
                .socialMediaProfileLinks(influencer.getSocialMediaProfileLinks().getImmutable())
                .build();
    }

    public Influencer toEntity(Keywords keywords) {

        return Influencer.builder()
                .uuid(uuid)
                .name(new InfluencerName(name))
                .introduction(new InfluencerIntroduction(introduction))
                .description(new InfluencerDescription(description))
                .profileImage(new ProfileImage(URI.create(profileImageUri)))
                .keywords(keywords)
                .socialMediaProfileLinks(new SocialMediaProfileLinks(socialMediaProfileLinks))
                .createdDate(createdDate)
                .lastModifiedDate(lastModifiedDate)
                .build();
    }
}
