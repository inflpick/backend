package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.product.adapter.out.persistence.ProductNotFoundException;
import com.leesh.inflpick.product.core.Product;
import org.jetbrains.annotations.NotNull;

public interface ProductRepository {

    String save(Product product);

    long count();

    @NotNull Product getByUuid(@NotNull String uuid) throws ProductNotFoundException;
}
