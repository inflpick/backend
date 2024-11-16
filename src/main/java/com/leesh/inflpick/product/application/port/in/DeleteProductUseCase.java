package com.leesh.inflpick.product.application.port.in;

import com.leesh.inflpick.product.domain.vo.ProductId;

public interface DeleteProductUseCase {

    void delete(ProductId id);
}
