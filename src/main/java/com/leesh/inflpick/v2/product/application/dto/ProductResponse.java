package com.leesh.inflpick.v2.product.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.product.domain.Product;

import java.time.Instant;
import java.util.List;

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
}
