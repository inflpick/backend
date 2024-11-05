package com.leesh.inflpick.v2.product.application.port.out;

import com.leesh.inflpick.v2.product.domain.Product;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;

public interface CommandProductPort {

    ProductId save(Product product);

}
