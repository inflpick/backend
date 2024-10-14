package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.product.core.domain.Product;

import java.util.Collection;

public interface ProductQueryService {

    Product getById(String id);

    PageDetails<Collection<Product>> getPage(PageQuery query);
}
