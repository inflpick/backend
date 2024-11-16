package com.leesh.inflpick.product.application.port.out;

import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;

import java.util.Optional;

public interface QueryProductPort {

    Optional<Product> query(ProductId productId);

    PageResponse<Product> query(PageRequest request);
}
