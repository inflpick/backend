package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.MissingRequiredFieldsException;
import com.leesh.inflpick.influencer.core.domain.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLink;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfluencerRequestTest {

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 수 있다.")
    @Test
    void toCommand() {
        // given
        SocialMediaRequest socialMediaRequest = new SocialMediaRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        SocialMediaProfileLink socialMediaProfileLink = socialMediaRequest.toEntity();
        String profileImageUri = "http://example.com/profile.jpg";
        InfluencerRequest request = new InfluencerRequest(
                "짐종국",
                "introduction",
                "description",
                profileImageUri,
                List.of(keywordId),
                List.of(socialMediaRequest)
        );

        // when
        InfluencerCreateCommand command = request.toCommand();

        // then
        assertThat(command).isNotNull();
        assertThat(command.name().name()).isEqualTo("짐종국");
        assertThat(command.description().description()).isEqualTo("description");
        assertThat(command.profileImage().uri()).isEqualTo(URI.create(profileImageUri));
        assertThat(command.socialMediaProfileLinks().hasSocialMedia(SocialMediaPlatform.TIKTOK)).isTrue();
        assertThat(command.socialMediaProfileLinks().getSocialMediaLink(SocialMediaPlatform.TIKTOK)).isEqualTo(socialMediaProfileLink);

    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 이름이 null인 경우 예외가 발생한다.")
    @Test
    void toCommandThrowsExceptionWhenNameIsNull() {
        SocialMediaRequest socialMediaRequest = new SocialMediaRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        assertThrows(MissingRequiredFieldsException.class, () -> new InfluencerRequest(
                null,
                "introduction",
                "description",
                "http://example.com/profile.jpg",
                List.of(keywordId),
                List.of(socialMediaRequest)
        ));
    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 설명이 null인 경우 예외가 발생한다.")
    @Test
    void toCommandThrowsExceptionWhenDescriptionIsNull() {
        SocialMediaRequest socialMediaRequest = new SocialMediaRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        assertThrows(MissingRequiredFieldsException.class, () -> new InfluencerRequest(
                "짐종국",
                "introduction",
                null,
                "http://example.com/profile.jpg",
                List.of(keywordId),
                List.of(socialMediaRequest)
        ));
    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 프로필 이미지 URI가 null인 경우 예외가 발생한다.")
    @Test
    void toCommandThrowsExceptionWhenProfileImageUriIsNull() {
        SocialMediaRequest socialMediaRequest = new SocialMediaRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        assertThrows(MissingRequiredFieldsException.class, () -> new InfluencerRequest(
                "짐종국",
                "introduction",
                "description",
                null,
                List.of(keywordId),
                List.of(socialMediaRequest)
        ));
    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 소셜 미디어 링크가 null인 경우 빈 SocialMediaLinks 객체로 변환한다.")
    @Test
    void toCommandThrowsExceptionWhenSocialMediaLinksAreNull() {
        String keywordId = "keywordId";
        InfluencerRequest request = new InfluencerRequest(
                "짐종국",
                "introduction",
                "description",
                "http://example.com/profile.jpg",
                List.of(keywordId),
                null
        );

        // when
        InfluencerCreateCommand command = request.toCommand();

        // then
        assertThat(command).isNotNull();
        assertThat(command.name().name()).isEqualTo("짐종국");
        assertThat(command.description().description()).isEqualTo("description");
        assertThat(command.profileImage().uri()).isEqualTo(URI.create("http://example.com/profile.jpg"));
        assertThat(command.socialMediaProfileLinks().isEmpty()).isTrue();

    }

}