package com.leesh.inflpick.product.adapter.in.web.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
import com.leesh.inflpick.product.adapter.in.web.docs.ProductResponseApiDocs;
import com.leesh.inflpick.product.core.domain.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
public record ProductWebResponse(
        String id,
        String name,
        String description,
        String productImageUrl,
        List<KeywordResponse> keywords,
        List<OnlineStoreLinkResponse> onlineStoreLinks,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant createdDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant lastModifiedDate) implements ProductResponseApiDocs {

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
