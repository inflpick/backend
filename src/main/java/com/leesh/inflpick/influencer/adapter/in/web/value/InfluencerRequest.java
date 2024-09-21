package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.exception.MissingRequiredFieldsException;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.*;
import com.leesh.inflpick.influencer.port.in.InfluencerCreateCommand;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Schema(name = "인플루언서 생성 요청", description = "인플루언서 생성 요청 필드")
@Builder
public record InfluencerRequest(
        @Schema(description = "이름 (최소 1자, 최대 300자)", example = "도날드 트럼프", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED, minLength = InfluencerName.MIN_LENGTH, maxLength = InfluencerName.MAX_LENGTH)
        String name,
        @Schema(description = "인물을 잘 나타낼 수 있는 짧은 소개", example = "미국 45대 대통령, 트럼프 주식회사 CEO, 정치가", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String introduction,
        @Schema(description = "인물에 대한 설명", example = "Hillary Diane Rodham Clinton is a politician, diplomat, lawyer, writer, and public speaker. She served as First Lady of the United States from 1993 to 2001, as a United States senator from New York from 2001 to 2009, and as the 67th United States secretary of state from 2009 until 2013.", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String description,
        @Schema(description = "프로필 이미지 URI (URI 형식의 문자열)", example = "https://example.com/profile.jpg", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String profileImageUri,
//        @Schema(description = "키워드 ID 목록", type = "array", example = "[\"92624c72-1cf2-4762-8c45-fe1a1f0a3e97\", \"8eabcaa9-5c70-46a5-a7c0-b580b1d20316\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        @ArraySchema(arraySchema = @Schema(description = "인플루언서에 등록할 키워드 UUID 목록", example = "[\"92624c72-1cf2-4762-8c45-fe1a1f0a3e97\", \"8eabcaa9-5c70-46a5-a7c0-b580b1d20316\"]", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<String> keywordUuids,
        @ArraySchema(arraySchema = @Schema(description = "인플루언서의 소셜 미디어 링크 목록", implementation = SocialMediaRequest.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<SocialMediaRequest> socialMediaLinks
) {

    public InfluencerRequest(@Nullable String name,
                             @Nullable String introduction,
                             @Nullable String description,
                             @Nullable String profileImageUri,
                             @Nullable List<String> keywordUuids,
                             @Nullable List<SocialMediaRequest> socialMediaLinks) {
        validateRequiredFields(name, introduction, description, profileImageUri);
        this.name = name.strip();
        this.introduction = introduction.strip();
        this.description = description.strip();
        this.profileImageUri = profileImageUri.strip();
        this.keywordUuids = keywordUuids == null ? new ArrayList<>() : keywordUuids.stream().map(String::strip).toList();
        this.socialMediaLinks = socialMediaLinks == null ? new ArrayList<>() : socialMediaLinks;
    }

    public @NotNull InfluencerCreateCommand toCommand() {

        validateRequiredFields(name, introduction, description, profileImageUri);

        InfluencerName influencerName = new InfluencerName(name);
        InfluencerDescription influencerDescription = new InfluencerDescription(description);
        InfluencerIntroduction influencerIntroduction = new InfluencerIntroduction(introduction);
        ProfileImage profileImage = ProfileImage.of(profileImageUri);
        SocialMediaProfileLinks socialMediaProfileLinks = convertToSocialMediaProfileEntity(socialMediaLinks);

        return new InfluencerCreateCommand(
            influencerName,
            influencerIntroduction,
            influencerDescription,
            profileImage,
            new HashSet<>(keywordUuids),
            socialMediaProfileLinks
        );
    }

    private @NotNull SocialMediaProfileLinks convertToSocialMediaProfileEntity(@NotNull List<SocialMediaRequest> links) {
        List<SocialMediaProfileLink> list = links
                .stream()
                .map(SocialMediaRequest::toEntity)
                .toList();
        return new SocialMediaProfileLinks(list);
    }

    private void validateRequiredFields(@Nullable String name,
                                        @Nullable String introduction,
                                        @Nullable String description,
                                        @Nullable String profileImageUri) {
        if (name == null || introduction == null || description == null || profileImageUri == null ||
                name.isBlank() || introduction.isBlank() || description.isBlank() || profileImageUri.isBlank()) {
            throw new MissingRequiredFieldsException("필수 입력 값이 입력되지 않음");
        }
    }

}
