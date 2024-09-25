package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductPageQuery;

import java.util.List;

public interface ProductQueryService {
    Product getById(String id);

    PageDetails<List<Product>> getPage(ProductPageQuery query);
}
