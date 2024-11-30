package com.leesh.inflpick.product.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.product.domain.Product;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // empty() 메서드에서 빈 json 응답을 보내기 위해 필요
public record ProductResponse(String id,
                              String name,
                              String description,
                              String productImageUrl,
                              List<KeywordResponse> keywords,
                              List<OnlineStoreLinkResponse> onlineStoreLinks,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                              Instant createdDate,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                              Instant lastModifiedDate) {


    public static ProductResponse create(Product product,
                                         List<Keyword> keywords,
                                         String productImageUrl) {

        List<OnlineStoreLinkResponse> onlineStoreLinkResponses = product.onlineStoreLinks().links().stream()
                .map(OnlineStoreLinkResponse::create)
                .toList();

        List<KeywordResponse> keywordResponses = keywords.stream()
                .map(KeywordResponse::create)
                .toList();

        return new ProductResponse(product.id().id(),
                product.name().name(),
                product.description().description(),
                productImageUrl,
                keywordResponses,
                onlineStoreLinkResponses,
                product.createdDate(),
                product.lastModifiedDate());
    }

    public static ProductResponse empty() {
        return new ProductResponse(null, null, null, null, null, null, null, null);
    }
}
