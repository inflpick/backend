package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface DeleteProductUseCase {

    void delete(ProductId id);
}
