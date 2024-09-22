package com.leesh.inflpick.influencer.adapter.in.web.value;

import com.leesh.inflpick.common.adapter.in.web.RequiredFieldsValidator;
import com.leesh.inflpick.influencer.core.domain.SocialMediaProfileLinks;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerDescription;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerIntroduction;
import com.leesh.inflpick.influencer.core.domain.value.InfluencerName;
import com.leesh.inflpick.influencer.core.domain.value.SocialMediaProfileLink;
import com.leesh.inflpick.influencer.core.service.InfluencerCommand;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(name = "인플루언서 생성 요청", description = "인플루언서 생성 요청 필드")
@Builder
public record InfluencerRequest(
        @Schema(description = "이름 (최소 1자, 최대 300자)", example = "핏블리", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED, minLength = InfluencerName.MIN_LENGTH, maxLength = InfluencerName.MAX_LENGTH)
        String name,
        @Schema(description = "인물을 잘 나타낼 수 있는 짧은 소개", example = "➞ 핏블리(문석기) PROFILE \u2028국제트레이너&스포츠영양코치", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String introduction,
        @Schema(description = "인물에 대한 설명", example = "“운동독학, 누구나 쉽고 전문적으로”\u2028국제트레이너 / 스포츠영양코치 / BJ치즈볼 / 타락헬창\u202840여개국 세계여행&전문 자격증으로 탄생한 핏블리", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String description,
        @ArraySchema(arraySchema = @Schema(description = "인플루언서에 등록할 키워드 ID 목록 (기본값: 빈 배열)", example = "[\"92624c72-1cf2-4762-8c45-fe1a1f0a3e97\", \"8eabcaa9-5c70-46a5-a7c0-b580b1d20316\"]", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<String> keywordIds,
        @ArraySchema(arraySchema = @Schema(description = "인플루언서의 소셜 미디어 프로필 링크 목록 (기본값: 빈 배열)", implementation = SocialMediaProfileRequest.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
        List<SocialMediaProfileRequest> socialMediaProfileLinks
) {

    public InfluencerRequest(@Nullable String name,
                             @Nullable String introduction,
                             @Nullable String description,
                             @Nullable List<String> keywordIds,
                             @Nullable List<SocialMediaProfileRequest> socialMediaProfileLinks) {
        RequiredFieldsValidator.validate(name, introduction, description);
        assert name != null;
        this.name = name.strip();
        assert introduction != null;
        this.introduction = introduction.strip();
        assert description != null;
        this.description = description.strip();
        this.keywordIds = keywordIds == null ? new ArrayList<>() : keywordIds.stream().map(String::strip).toList();
        this.socialMediaProfileLinks = socialMediaProfileLinks == null ? new ArrayList<>() : socialMediaProfileLinks.stream()
                .map(socialMediaProfileRequest -> new SocialMediaProfileRequest(socialMediaProfileRequest.platform().strip(), socialMediaProfileRequest.uri().strip()))
                .toList();
    }

    public @NotNull InfluencerCommand toCommand() {
        RequiredFieldsValidator.validate(name, introduction, description);
        InfluencerName influencerName = new InfluencerName(name);
        InfluencerDescription influencerDescription = new InfluencerDescription(description);
        InfluencerIntroduction influencerIntroduction = new InfluencerIntroduction(introduction);

        return new InfluencerCommand(
            influencerName,
            influencerIntroduction,
            influencerDescription,
            new HashSet<>(keywordIds),
            convertToSocialMediaProfileEntity(socialMediaProfileLinks)
        );
    }

    private @NotNull SocialMediaProfileLinks convertToSocialMediaProfileEntity(@NotNull List<SocialMediaProfileRequest> links) {
        Set<SocialMediaProfileLink> list = links
                .stream()
                .map(SocialMediaProfileRequest::toEntity)
                .collect(Collectors.toSet());
        return new SocialMediaProfileLinks(list);
    }
}
