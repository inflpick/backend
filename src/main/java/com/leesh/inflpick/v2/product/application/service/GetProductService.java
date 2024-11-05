package com.leesh.inflpick.v2.product.application.service;

import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.v2.product.application.port.in.GetProductUseCase;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.common.application.port.out.storage.StoragePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class GetProductService implements GetProductUseCase {

    private final QueryProductPort queryProductPort;
    private final StoragePort storagePort;

    @Override
    public GetProductResponse get(ProductId id) {
        Product product = queryProductPort.query(id).orElseThrow(() -> new ProductNotFoundException(id));
        String productImagePath = product.image().path();
        String productImageUrl = storagePort.getUrlString(productImagePath);
        return GetProductResponse.from(product, productImageUrl);
    }
}
