package com.leesh.inflpick.product.application.port.out;

import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;

public interface CommandProductPort {

    ProductId save(Product product);

    void delete(ProductId id);
}
