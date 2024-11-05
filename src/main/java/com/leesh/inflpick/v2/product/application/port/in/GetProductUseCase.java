package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface GetProductUseCase {

    GetProductResponse get(ProductId id);

}
