package com.leesh.inflpick.v2.product.application.service;

import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.product.application.dto.UpdateProductRequest;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.v2.product.application.port.in.UpdateProductUseCase;
import com.leesh.inflpick.v2.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.product.domain.vo.ProductKeyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateProductService implements UpdateProductUseCase {

    private final CommandProductPort commandProductPort;
    private final QueryKeywordPort queryKeywordPort;
    private final QueryProductPort queryProductPort;

    @Override
    public void update(ProductId id, UpdateProductRequest request) {
        Product product = queryProductPort.query(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        List<ProductKeyword> existProductKeywords = queryKeywordPort.query(request.keywordIds())
                .stream().map(keyword -> ProductKeyword.create(keyword.id(), keyword.name(), keyword.color()))
                .toList();
        Product updatedProduct = product.update(request.name(), request.description(), existProductKeywords, request.onlineStoreLinks());
        commandProductPort.save(updatedProduct);
    }
}
