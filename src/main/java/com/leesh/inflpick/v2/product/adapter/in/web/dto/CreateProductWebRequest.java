package com.leesh.inflpick.v2.product.adapter.in.web.dto;

import com.leesh.inflpick.v2.common.application.service.RequiredFieldsValidator;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.product.application.dto.CreateProductRequest;
import com.leesh.inflpick.v2.product.domain.OnlineStoreLinks;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.ProductDescription;
import com.leesh.inflpick.v2.product.domain.vo.ProductName;

import java.util.List;

public record CreateProductWebRequest(String name,
                                      String description,
                                      List<String> keywordIds,
                                      List<OnlineStoreLinkWebRequest> onlineStoreLinks) {

    public CreateProductWebRequest(String name,
                                   String description,
                                   List<String> keywordIds,
                                   List<OnlineStoreLinkWebRequest> onlineStoreLinks) {
        RequiredFieldsValidator.validate(name, description);
        this.name = name.strip();
        this.description = description.strip();
        this.keywordIds = keywordIds == null ? List.of() : stripKeywordIds(keywordIds);
        this.onlineStoreLinks = onlineStoreLinks == null ? List.of() : stripOnlineStoreLinks(onlineStoreLinks);
    }

    private static List<OnlineStoreLinkWebRequest> stripOnlineStoreLinks(List<OnlineStoreLinkWebRequest> onlineStoreLinks) {
        return onlineStoreLinks.stream()
                .map(onlineStoreLinkWebRequest -> {
                    String strippedPlatform = onlineStoreLinkWebRequest.platform().strip();
                    String strippedUrl = onlineStoreLinkWebRequest.url().strip();
                    return new OnlineStoreLinkWebRequest(strippedPlatform, strippedUrl);
                }).toList();
    }

    private static List<String> stripKeywordIds(List<String> keywordIds) {
        return keywordIds.stream()
                .map(String::strip)
                .toList();
    }

    public CreateProductRequest toRequest() {
        ProductName name = ProductName.create(this.name);
        ProductDescription description = ProductDescription.create(this.description);
        Keywords keywords = Keywords.createIdString(keywordIds);
        OnlineStoreLinks onlineStoreLinks = convertToEntities();
        return new CreateProductRequest(name, description, null, null);
    }

    private OnlineStoreLinks convertToEntities() {
        List<OnlineStoreLink> links = this.onlineStoreLinks.stream()
                .map(OnlineStoreLinkWebRequest::toEntity)
                .toList();
        return null;
    }

}
