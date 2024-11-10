package com.leesh.inflpick.v2.product.application.port.out;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

import java.util.Optional;

public interface QueryProductPort {

    Optional<Product> query(ProductId productId);

    PageResponse<Product> query(PageRequest request);
}
