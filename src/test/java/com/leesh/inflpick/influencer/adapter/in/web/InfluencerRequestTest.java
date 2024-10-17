package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerWebRequest;
import com.leesh.inflpick.influencer.adapter.in.web.value.SocialMediaProfileRequest;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaPlatform;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import com.leesh.inflpick.influencer.port.InfluencerCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfluencerRequestTest {

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 수 있다.")
    @Test
    void toCommand() {
        // given
        SocialMediaProfileRequest socialMediaProfileRequest = new SocialMediaProfileRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        SocialMediaProfileLink socialMediaProfileLink = socialMediaProfileRequest.toEntity();
        String profileImagePath = "http://example.com/profile.jpg";
        InfluencerWebRequest request = new InfluencerWebRequest(
                "짐종국",
                "introduction",
                "description",
                List.of(keywordId),
                List.of(socialMediaProfileRequest)
        );

        // when
        InfluencerCommand command = request.toCommand();

        // then
        assertThat(command).isNotNull();
        assertThat(command.name().name()).isEqualTo("짐종국");
        assertThat(command.description().description()).isEqualTo("description");
        assertThat(command.socialMediaProfileLinks().hasSocialMedia(SocialMediaPlatform.TIKTOK)).isTrue();
        assertThat(command.socialMediaProfileLinks().getSocialMediaLink(SocialMediaPlatform.TIKTOK)).isEqualTo(socialMediaProfileLink);

    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 이름이 null인 경우 예외가 발생한다.")
    @Test
    void toCommandThrowsExceptionWhenNameIsNull() {
        SocialMediaProfileRequest socialMediaProfileRequest = new SocialMediaProfileRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        assertThrows(MissingRequiredFieldsException.class, () -> new InfluencerWebRequest(
                null,
                "introduction",
                "description",
                List.of(keywordId),
                List.of(socialMediaProfileRequest)
        ));
    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 설명이 null인 경우 예외가 발생한다.")
    @Test
    void toCommandThrowsExceptionWhenDescriptionIsNull() {
        SocialMediaProfileRequest socialMediaProfileRequest = new SocialMediaProfileRequest(SocialMediaPlatform.TIKTOK.name(), "http://example.com/profile.jpg");
        String keywordId = "keywordId";
        assertThrows(MissingRequiredFieldsException.class, () -> new InfluencerWebRequest(
                "짐종국",
                "introduction",
                null,
                List.of(keywordId),
                List.of(socialMediaProfileRequest)
        ));
    }

    @DisplayName("toCommand() 메서드를 통해 InfluencerCreateCommand 객체로 변환할 때 소셜 미디어 링크가 null인 경우 빈 SocialMediaLinks 객체로 변환한다.")
    @Test
    void toCommandThrowsExceptionWhenSocialMediaLinksAreNull() {
        String keywordId = "keywordId";
        InfluencerWebRequest request = new InfluencerWebRequest(
                "짐종국",
                "introduction",
                "description",
                List.of(keywordId),
                null
        );

        // when
        InfluencerCommand command = request.toCommand();

        // then
        assertThat(command).isNotNull();
        assertThat(command.name().name()).isEqualTo("짐종국");
        assertThat(command.description().description()).isEqualTo("description");
        assertThat(command.socialMediaProfileLinks().isEmpty()).isTrue();

    }

}