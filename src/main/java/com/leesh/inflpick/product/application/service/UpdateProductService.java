package com.leesh.inflpick.product.application.service;

import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.product.application.dto.ProductRequest;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.product.application.port.in.UpdateProductUseCase;
import com.leesh.inflpick.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;
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
    public void update(ProductId id, ProductRequest request) {
        Product product = queryProductPort.query(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        List<KeywordId> keywordIds = request.keywordIds().stream().map(KeywordId::create).toList();
        List<Keyword> keywords = queryKeywordPort.query(keywordIds);
        Product updatedProduct = product.update(request);
        Product putKeywordProduct = updatedProduct.putKeywords(keywords);
        commandProductPort.save(putKeywordProduct);
    }
}
