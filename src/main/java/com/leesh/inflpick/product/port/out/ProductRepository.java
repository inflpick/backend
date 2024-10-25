package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.v2.shared.application.dto.PageRequest;
import com.leesh.inflpick.v2.shared.application.dto.PageResponse;
import com.leesh.inflpick.product.core.domain.Product;
import org.jetbrains.annotations.NotNull;

public interface ProductRepository {

    String save(Product product);

    long count();

    @NotNull Product getById(@NotNull String id) throws ProductNotFoundException;

    PageResponse<Product> getPage(PageRequest request);

    void deleteById(String id);
}
