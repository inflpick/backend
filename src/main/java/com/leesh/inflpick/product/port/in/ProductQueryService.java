package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.product.core.Product;

public interface ProductQueryService {
    Product getById(String id);
}
