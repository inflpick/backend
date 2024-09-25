package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductPageQuery;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ProductRepository {

    String save(Product product);

    long count();

    @NotNull Product getById(@NotNull String id) throws ProductNotFoundException;

    PageDetails<List<Product>> getPage(ProductPageQuery query);

    void deleteById(String id);
}
