package com.leesh.inflpick.product.application.service;

import com.leesh.inflpick.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.keyword.domain.Keyword;
import com.leesh.inflpick.keyword.domain.vo.KeywordId;
import com.leesh.inflpick.product.application.dto.ProductRequest;
import com.leesh.inflpick.product.application.port.in.CreateProductUseCase;
import com.leesh.inflpick.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateProductService implements CreateProductUseCase {

    private final CommandProductPort commandProductPort;
    private final QueryKeywordPort queryKeywordPort;

    @Override
    public ProductId create(ProductRequest request) {
        List<KeywordId> keywordIds = request.keywordIds().stream()
                .map(KeywordId::create)
                .toList();
        List<Keyword> existsKeywords = queryKeywordPort.query(keywordIds);
        Product product = request.toEntity();
        Product addKeywordProduct = product.addKeywords(existsKeywords);
        return commandProductPort.save(addKeywordProduct);
    }
}
