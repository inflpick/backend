package com.leesh.inflpick.product.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
import com.leesh.inflpick.product.core.domain.Product;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Schema(name = "제품 조회 응답")
@Builder
public record ProductWebResponse(
        @Schema(description = "ID", example = "f103314b-778c-49fc-ae9c-7956794a3bdf", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String id,
        @Schema(description = "제품명", example = "마이프로틴 “6레이어 프로틴바” (육겹바)", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String name,
        @Schema(description = "제품 설명", example = "프로틴 스낵 베스트셀러 중 하나로 6겹의 뛰어난 맛과 식감을 자랑합니다.\n 1개당 20g 이상의 단백질과 탄수화물뿐만 아니라 우리 몸에 필요한 식이섬유를 풍부하게 함유하였습니다. 6 레이어 프로틴바로 맛과 영양을 모두 챙기세요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String description,
        @Schema(description = "제품 이미지 URL", example = "https://cdn.inflpick.com/product-image.jpg", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
        String productImageUrl,
        @ArraySchema(schema = @Schema(description = "제품 키워드 목록", implementation = KeywordResponse.class, requiredMode = Schema.RequiredMode.REQUIRED))
        List<KeywordResponse> keywords,
        @ArraySchema(schema = @Schema(description = "제품의 온라인 스토어 링크 목록", implementation = OnlineStoreLinkResponse.class, requiredMode = Schema.RequiredMode.REQUIRED))
        List<OnlineStoreLinkResponse> onlineStoreLinks,
        @Schema(description = "생성일 (UTC)", example = "2021-07-01T00:00:00Z", implementation = Instant.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant createdDate,
        @Schema(description = "마지막 수정일 (UTC)", example = "2021-07-01T00:00:00Z", implementation = Instant.class, requiredMode = Schema.RequiredMode.REQUIRED)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant lastModifiedDate) {

    public static ProductWebResponse from(Product product, String productImageUrl) {

        List<KeywordResponse> keywordResponses = product.getKeywords().keywords().stream()
                .map(KeywordResponse::from)
                .toList();

        List<OnlineStoreLinkResponse> onlineStoreLinkResponses = product.getOnlineStoreLinks().links().stream()
                .map(OnlineStoreLinkResponse::from)
                .toList();

        return ProductWebResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .productImageUrl(productImageUrl)
                .keywords(keywordResponses)
                .onlineStoreLinks(onlineStoreLinkResponses)
                .createdDate(product.getCreatedDate())
                .lastModifiedDate(product.getLastModifiedDate())
                .build();
    }
}
