package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.application.dto.UpdateProductRequest;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface UpdateProductUseCase {

    void update(ProductId id, UpdateProductRequest request);
}
