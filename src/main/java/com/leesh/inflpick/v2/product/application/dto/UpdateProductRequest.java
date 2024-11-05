package com.leesh.inflpick.v2.product.application.dto;

import com.leesh.inflpick.v2.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.v2.product.domain.vo.OnlineStoreLink;
import com.leesh.inflpick.v2.product.domain.vo.ProductDescription;
import com.leesh.inflpick.v2.product.domain.vo.ProductName;

import java.util.List;

public record UpdateProductRequest(ProductName name,
                                   ProductDescription description,
                                   List<KeywordId> keywordIds,
                                   List<OnlineStoreLink> onlineStoreLinks) {
}
