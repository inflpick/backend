package com.leesh.inflpick.v2.influencer.adapter.out.persistence.mongo;

import com.leesh.inflpick.v2.influencer.domain.Influencer;
import com.leesh.inflpick.v2.influencer.domain.SnsProfileLinks;
import com.leesh.inflpick.v2.influencer.domain.vo.*;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "influencers")
public record InfluencerDocument(@Id String id,
                                  String name,
                                  String introduction,
                                  String description,
                                  String profileImagePath,
                                  List<String> keywordIds,
                                  List<SnsProfileLinkDocument> snsProfileLinks,
                                  @CreatedBy String createdBy,
                                  @CreatedDate Instant createdDate,
                                  @LastModifiedBy String lastModifiedBy,
                                  @LastModifiedDate Instant lastModifiedDate) {

    public static InfluencerDocument from(Influencer influencer) {

        String id = influencer.id().isEmpty() ? null : influencer.id().id();
        InfluencerName name = influencer.name();
        InfluencerIntroduction introduction = influencer.introduction();
        InfluencerDescription description = influencer.description();
        ProfileImage profileImage = influencer.profileImage();
        List<String> keywordIds = influencer.keywords().ids()
                .stream()
                .map(KeywordId::id)
                .toList();
        List<SnsProfileLinkDocument> snsProfileLinkDocuments = influencer.snsProfileLinks().links()
                .stream()
                .map(SnsProfileLinkDocument::from)
                .toList();

        return new InfluencerDocument(
                id,
                name.name(),
                introduction.introduction(),
                description.description(),
                profileImage.path(),
                keywordIds,
                snsProfileLinkDocuments,
                influencer.createdBy(),
                influencer.createdDate(),
                influencer.lastModifiedBy(),
                influencer.lastModifiedDate());
    }

    public Influencer toEntity() {

        InfluencerId id = InfluencerId.create(this.id);
        InfluencerName name = InfluencerName.create(this.name);
        InfluencerIntroduction introduction = InfluencerIntroduction.create(this.introduction);
        InfluencerDescription description = InfluencerDescription.create(this.description);
        ProfileImage profileImage = ProfileImage.create(this.profileImagePath);
        List<SnsProfileLink> snsProfileLinks = this.snsProfileLinks.stream()
                .map(SnsProfileLinkDocument::toEntity)
                .collect(Collectors.toList());
        SnsProfileLinks profileLinks = SnsProfileLinks.create(snsProfileLinks);
        return Influencer.withId(id,
                name,
                introduction,
                description,
                profileImage,
                profileLinks,
                Keywords.createIdString(keywordIds),
                createdDate,
                createdBy,
                lastModifiedDate,
                lastModifiedBy);

    }
}
