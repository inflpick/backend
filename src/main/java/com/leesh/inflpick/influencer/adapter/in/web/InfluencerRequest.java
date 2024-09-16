package com.leesh.inflpick.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.core.domain.*;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Schema(name = "인플루언서 생성")
public record InfluencerRequest(
        @Schema(description = "인플루언서 이름 (최소 1자, 최대 300자)", example = "짐종국", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED, minLength = InfluencerName.MIN_LENGTH, maxLength = InfluencerName.MAX_LENGTH)
        @Nullable
        String name,
        @Schema(description = "인플루언서 설명 (최소 1자 이상)", example = "짐종국은 유명한 인플루언서입니다.", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED, minLength = InfluencerDescription.MIN_LENGTH, maxLength = InfluencerDescription.MAX_LENGTH)
        @Nullable
        String description,
        @Schema(description = "인플루언서 프로필 이미지 URI (URI 형식의 문자열)", example = "https://example.com/profile.jpg", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @Nullable
        String profileImageUri,
        @ArraySchema(schema = @Schema(implementation = SocialMediaRequest.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "소셜 미디어 링크 목록 (기본값: 빈 배열)"))
        @Nullable
        List<SocialMediaRequest> socialMediaLinks
) {

    public InfluencerCreateCommand toCommand() {

        InfluencerName validatedName = validateAndCreateName(name);
        InfluencerDescription validatedDescription = validateAndCreateDescription(description);
        ProfileImage validatedProfileImage = validateAndCreateProfileImageUri(profileImageUri);
        SocialMediaLinks validatedSocialMediaLinks = createDefaultIfNull(socialMediaLinks);
        try {
            return new InfluencerCreateCommand(
                validatedName,
                validatedDescription,
                validatedProfileImage,
                validatedSocialMediaLinks
            );
        } catch (ConstraintViolationException e) {
            throw new UserInputException(e);
        }
    }

    private SocialMediaLinks createDefaultIfNull(@Nullable List<SocialMediaRequest> socialMediaLinks) {
        if (socialMediaLinks == null) {
            return new SocialMediaLinks(List.of());
        }
        List<SocialMediaLink> links = socialMediaLinks.stream()
                .map(SocialMediaRequest::toEntity)
                .toList();
        return new SocialMediaLinks(links);
    }

    private ProfileImage validateAndCreateProfileImageUri(@Nullable String profileImageUri) {
        if (profileImageUri == null || profileImageUri.isBlank()) {
            throw new UserInputException("인플루언서 프로필 이미지 URI를 입력해주세요.", "");
        }
        try {
            URI uri = new URI(profileImageUri);
            return new ProfileImage(uri);
        } catch (IllegalArgumentException | URISyntaxException e) {
            throw new UserInputException("올바른 URI 형식이 아닙니다.", profileImageUri);
        }
    }

    private InfluencerDescription validateAndCreateDescription(@Nullable String description) {
        if (description == null || description.isBlank()) {
            throw new UserInputException("인플루언서 설명을 입력해주세요.", "");
        }
        try {
            return new InfluencerDescription(description);
        } catch (ConstraintViolationException e) {
            throw new UserInputException(e);
        }
    }

    private InfluencerName validateAndCreateName(@Nullable String name) {
        if (name == null || name.isBlank()) {
            throw new UserInputException("인플루언서 이름을 입력해주세요.", "");
        }
        try {
            return new InfluencerName(name);
        } catch (ConstraintViolationException e) {
            throw new UserInputException(e);
        }
    }
}
