package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.product.core.Product;

public interface ProductRepository {

    String save(Product product);
}
