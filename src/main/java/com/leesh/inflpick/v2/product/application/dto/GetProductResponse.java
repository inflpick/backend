package com.leesh.inflpick.v2.product.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.ProductKeyword;

import java.time.Instant;
import java.util.List;

public record GetProductResponse(String id,
                                 String name,
                                 String description,
                                 String productImageUrl,
                                 List<ProductKeywordResponse> keywords,
                                 List<OnlineStoreLinkResponse> onlineStoreLinks,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                 Instant createdDate,
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
                                 Instant lastModifiedDate) {

    record ProductKeywordResponse(String id, String name, String hexColor) {
        public static ProductKeywordResponse from(ProductKeyword keyword) {
            return new ProductKeywordResponse(keyword.id().id(), keyword.name().name(), keyword.color().hexColor());
        }
    }

    record OnlineStoreLinkResponse(String platform, String url) {
        public static OnlineStoreLinkResponse from(OnlineStoreLink onlineStoreLink) {
            return new OnlineStoreLinkResponse(onlineStoreLink.platform().name(), onlineStoreLink.url());
        }
    }

    public static GetProductResponse from(Product product, String productImageUrl) {
        List<ProductKeywordResponse> keywordResponses = product.keywords().keywords().stream()
                .map(ProductKeywordResponse::from)
                .toList();
        List<OnlineStoreLinkResponse> onlineStoreLinkResponses = product.onlineStoreLinks().links().stream()
                .map(OnlineStoreLinkResponse::from)
                .toList();
        return new GetProductResponse(product.id().id(),
                product.name().name(),
                product.description().description(),
                productImageUrl,
                keywordResponses,
                onlineStoreLinkResponses,
                product.createdDate(),
                product.lastModifiedDate());
    }
}
