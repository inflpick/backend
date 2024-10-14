package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.product.core.domain.Product;

public interface ProductQueryService {

    Product query(String id);

    PageResponse<Product> query(PageRequest request);
}
