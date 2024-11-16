package com.leesh.inflpick.product.domain;

import com.leesh.inflpick.product.domain.vo.ProductKeyword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record ProductKeywords(List<ProductKeyword> keywords) {

    public ProductKeywords {
        keywords = Collections.unmodifiableList(keywords);
    }

    /* Business Logic */
    static ProductKeywords create(List<ProductKeyword> keywords) {
        return new ProductKeywords(keywords);
    }

    static ProductKeywords empty() {
        return new ProductKeywords(List.of());
    }

    ProductKeywords addAll(List<ProductKeyword> keywords) {
        List<ProductKeyword> modifiableList = new ArrayList<>(this.keywords);
        modifiableList.addAll(keywords);
        return new ProductKeywords(modifiableList);
    }
}
