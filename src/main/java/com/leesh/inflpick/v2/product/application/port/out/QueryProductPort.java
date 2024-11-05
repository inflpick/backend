package com.leesh.inflpick.v2.product.application.port.out;

import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import com.leesh.inflpick.v2.common.application.dto.PageRequestTemp;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;

import java.util.Optional;

public interface QueryProductPort {

    Optional<Product> query(ProductId productId);

    OffsetPageResponse<Product> query(PageRequestTemp request);
}
