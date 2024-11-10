package com.leesh.inflpick.v2.product.application.service;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.keyword.domain.Keyword;
import com.leesh.inflpick.v2.keyword.domain.Keywords;
import com.leesh.inflpick.v2.product.application.dto.ProductResponse;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.v2.product.application.port.in.GetProductUseCase;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetProductService implements GetProductUseCase {

    private final QueryProductPort queryProductPort;
    private final StoragePort storagePort;
    private final QueryKeywordPort queryKeywordPort;

    @Override
    public ProductResponse get(ProductId id) {
        Product product = queryProductPort.query(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        Keywords keywords = product.keywords();
        List<Keyword> productKeywords = queryKeywordPort.query(keywords.ids());
        String productImagePath = product.image().path();
        String productImageUrl = storagePort.getUrlString(productImagePath);
        return ProductResponse.create(product, productKeywords, productImageUrl);
    }

    @Override
    public PageResponse<ProductResponse> getPage(PageRequest request) {
        PageResponse<Product> productPage = queryProductPort.query(request);
        List<ProductResponse> productResponses = productPage.contents().stream()
                .map(product -> this.get(product.id()))
                .toList();
        return PageResponse.create(productResponses,
                productPage.currentPage(),
                productPage.totalPages(),
                productPage.size(),
                productPage.totalElements(),
                productPage.sortProperties());
    }
}
