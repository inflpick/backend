package com.leesh.inflpick.v2.product.application.service;

import com.leesh.inflpick.v2.keyword.application.port.out.QueryKeywordPort;
import com.leesh.inflpick.v2.product.application.dto.CreateProductRequest;
import com.leesh.inflpick.v2.product.application.port.in.CreateProductUseCase;
import com.leesh.inflpick.v2.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.product.domain.vo.ProductKeyword;
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
    public ProductId create(CreateProductRequest request) {
        Product product = request.toEntity();
        List<ProductKeyword> existProductKeywords = queryKeywordPort.query(request.keywordIds())
                .stream().map(keyword -> ProductKeyword.create(keyword.id(), keyword.name(), keyword.color()))
                .toList();
        product = product.addKeywords(existProductKeywords);
        return commandProductPort.save(product);
    }
}
