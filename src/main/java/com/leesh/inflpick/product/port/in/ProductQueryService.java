package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
import com.leesh.inflpick.product.core.domain.Product;

public interface ProductQueryService {

    Product query(String id);

    PageResponse<Product> query(PageRequest request);
}
