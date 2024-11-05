package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.application.dto.CreateProductRequest;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface CreateProductUseCase {

    ProductId create(CreateProductRequest request);
}
