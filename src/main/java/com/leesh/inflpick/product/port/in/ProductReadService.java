package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.product.core.Product;

public interface ProductReadService {
    Product getByUuid(String uuid);
}
