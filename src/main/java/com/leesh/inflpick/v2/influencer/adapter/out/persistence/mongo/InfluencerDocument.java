package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import lombok.Getter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "influencers")
@Getter
class InfluencerDocument {

    @Id
    private String id;
    private final String name;
    private final String introduction;
    private final String description;
    private final String profileImagePath;
    private final List<String> keywordIds;
    private final Set<SnsProfileLinkDocument> snsProfileLinkDocuments;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private Instant lastModifiedDate;

    InfluencerDocument(String name,
                       String introduction,
                       String description,
                       String profileImagePath,
                       List<String> keywordIds,
                       Set<SnsProfileLinkDocument> snsProfileLinkDocuments) {
        this.name = name;
        this.introduction = introduction;
        this.description = description;
        this.profileImagePath = profileImagePath;
        this.keywordIds = keywordIds;
        this.snsProfileLinkDocuments = snsProfileLinkDocuments;
    }

    static InfluencerDocument from(Influencer influencer) {

        InfluencerName name = influencer.getName();
        InfluencerIntroduction introduction = influencer.getIntroduction();
        InfluencerDescription description = influencer.getDescription();
        ProfileImage profileImage = influencer.getProfileImage();
        List<String> keywordIds = influencer.getKeywords().getIds()
                .stream()
                .map(KeywordId::getId)
                .toList();
        Set<SnsProfileLinkDocument> snsProfileLinkDocuments = influencer.getSnsProfileLinks()
                .getLinks().stream()
                .map(SnsProfileLinkDocument::from)
                .collect(Collectors.toSet());

        return new InfluencerDocument(
                name.getValue(),
                introduction.getValue(),
                description.getValue(),
                profileImage.getPath(),
                keywordIds,
                snsProfileLinkDocuments);
    }

    Influencer toEntity() {
        InfluencerName name = InfluencerName.create(this.name);
        InfluencerIntroduction introduction = InfluencerIntroduction.create(this.introduction);
        InfluencerDescription description = InfluencerDescription.create(this.description);
        ProfileImage profileImage = ProfileImage.create(this.profileImagePath);
        List<SnsProfileLink> snsProfileLinks = this.snsProfileLinkDocuments.stream()
                .map(SnsProfileLinkDocument::toEntity)
                .collect(Collectors.toList());
        Influencer influencer = Influencer.builder(name)
                .introduction(introduction)
                .description(description)
                .profileImage(profileImage)
                .createdBy(createdBy)
                .createdDate(createdDate)
                .lastModifiedBy(lastModifiedBy)
                .lastModifiedDate(lastModifiedDate)
                .build();
        influencer.addSnsProfileLinks(snsProfileLinks);
        return influencer;
    }
}
