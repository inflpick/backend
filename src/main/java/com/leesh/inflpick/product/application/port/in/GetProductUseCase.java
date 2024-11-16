package com.leesh.inflpick.product.application.port.in;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.product.application.dto.ProductResponse;
import com.leesh.inflpick.product.domain.vo.ProductId;

public interface GetProductUseCase {

    ProductResponse get(ProductId id);

    PageResponse<ProductResponse> getPage(PageRequest request);
}
