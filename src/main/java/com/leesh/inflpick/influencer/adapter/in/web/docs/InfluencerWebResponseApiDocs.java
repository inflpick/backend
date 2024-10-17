package com.leesh.inflpick.influencer.adapter.in.web.docs;

import com.leesh.inflpick.influencer.adapter.in.web.value.SocialMediaProfileLinkResponse;
import com.leesh.inflpick.keyword.adapter.in.web.docs.KeywordWebResponseApiDocs;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;

@Schema(name = "인플루언서 조회 응답")
public interface InfluencerWebResponseApiDocs {

    @Schema(description = "ID", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String id();

    @Schema(description = "이름", example = "도날드 트럼프", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "인물을 잘 나타낼 수 있는 짧은 소개", example = "미국 45대 대통령, 트럼프 주식회사 CEO, 정치가", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String introduction();

    @Schema(description = "인물에 대한 설명", example = "Hillary Diane Rodham Clinton is a politician, diplomat, lawyer, writer, and public speaker. She served as First Lady of the United States from 1993 to 2001, as a United States senator from New York from 2001 to 2009, and as the 67th United States secretary of state from 2009 until 2013.", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String description();

    @Schema(description = "프로필 이미지 URL", example = "https://cdn.inflpick.com/profile-image.jpg", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String profileImageUrl();

    @ArraySchema(schema = @Schema(description = "소셜 미디어 링크 목록", implementation = SocialMediaProfileLinkResponseApiDocs.class, requiredMode = Schema.RequiredMode.REQUIRED))
    List<SocialMediaProfileLinkResponse> socialMediaProfileLinks();

    @ArraySchema(schema = @Schema(description = "인물에 대한 키워드 목록", implementation = KeywordWebResponseApiDocs.class, requiredMode = Schema.RequiredMode.REQUIRED))
    List<KeywordResponse> keywords();

    @Schema(description = "생성일 (UTC)", example = "2021-07-01T00:00:00Z", implementation = Instant.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant createdDate();

    @Schema(description = "마지막 수정일 (UTC)", example = "2021-07-01T00:00:00Z", implementation = Instant.class, requiredMode = Schema.RequiredMode.REQUIRED)
    Instant lastModifiedDate();
}
