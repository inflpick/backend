package com.leesh.inflpick.product.application.port.in;

import com.leesh.inflpick.product.application.dto.ProductRequest;
import com.leesh.inflpick.product.domain.vo.ProductId;

public interface UpdateProductUseCase {

    void update(ProductId id, ProductRequest request);
}
