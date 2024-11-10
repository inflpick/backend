package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.product.application.dto.ProductResponse;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface GetProductUseCase {

    ProductResponse get(ProductId id);

    PageResponse<ProductResponse> getPage(PageRequest request);
}
