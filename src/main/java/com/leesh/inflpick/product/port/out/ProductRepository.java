package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.product.core.domain.Product;
import org.jetbrains.annotations.NotNull;

public interface ProductRepository {

    String save(Product product);

    long count();

    @NotNull Product getById(@NotNull String id) throws ProductNotFoundException;

    PageResponse<Product> getPage(PageRequest request);

    void deleteById(String id);
}
