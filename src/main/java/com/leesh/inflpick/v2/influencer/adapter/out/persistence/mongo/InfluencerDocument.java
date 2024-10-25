package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Builder(access = AccessLevel.PRIVATE, builderMethodName = "requiredBuilder")
@Document(collection = "influencers")
@Getter
class InfluencerDocument {

    @Id
    private final String id;
    private final String name;
    private final String introduction;
    private final String description;
    private final String profileImagePath;
    private final Set<String> keywordIds;
    private final Set<SnsProfileLinkDocument> snsProfileLinkDocuments;
    @CreatedBy
    private final String createdBy;
    @CreatedDate
    private final Instant createdDate;
    @LastModifiedBy
    private final String lastModifiedBy;
    @LastModifiedDate
    private final Instant lastModifiedDate;

    static InfluencerDocumentBuilder builder(String id,
                                             String name,
                                             String introduction,
                                             String description,
                                             String profileImagePath,
                                             Set<String> keywordIds,
                                             Set<SnsProfileLinkDocument> snsProfileLinkDocuments) {

        return requiredBuilder()
                .id(id)
                .name(name)
                .introduction(introduction)
                .description(description)
                .profileImagePath(profileImagePath)
                .keywordIds(keywordIds)
                .snsProfileLinkDocuments(snsProfileLinkDocuments);
    }

    static InfluencerDocument from(Influencer influencer) {

        InfluencerName name = influencer.getName();
        InfluencerIntroduction introduction = influencer.getIntroduction();
        InfluencerDescription description = influencer.getDescription();
        ProfileImage profileImage = influencer.getProfileImage();
        Set<String> keywordIds = influencer.getKeywordIds().stream().map(KeywordId::getValue).collect(Collectors.toSet());
        Set<SnsProfileLinkDocument> snsProfileLinkDocuments = influencer.getSnsProfileLinks()
                .getLinks().stream()
                .map(SnsProfileLinkDocument::from)
                .collect(Collectors.toSet());

        return InfluencerDocument.builder(
                influencer.getId().getValue(),
                name.getValue(),
                introduction.getValue(),
                description.getValue(),
                profileImage.getPath(),
                keywordIds,
                snsProfileLinkDocuments)
                .build();
    }

    Influencer toEntity() {
        InfluencerName name = InfluencerName.create(this.name);
        InfluencerIntroduction introduction = InfluencerIntroduction.create(this.introduction);
        InfluencerDescription description = InfluencerDescription.create(this.description);
        ProfileImage profileImage = ProfileImage.create(this.profileImagePath);
        Set<SnsProfileLink> snsProfileLinks = this.snsProfileLinkDocuments.stream()
                .map(SnsProfileLinkDocument::toEntity)
                .collect(Collectors.toSet());
        Influencer influencer = Influencer.builder(name)
                .introduction(introduction)
                .description(description)
                .profileImage(profileImage)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .lastModifiedBy(lastModifiedBy)
                .lastModifiedDate(lastModifiedDate)
                .build();
        Set<KeywordId> keywordIds = this.keywordIds.stream()
                .map(KeywordId::create)
                .collect(Collectors.toSet());
        influencer.addKeywordIds(keywordIds);
        influencer.addSnsProfileLinks(snsProfileLinks);
        return influencer;
    }
}
