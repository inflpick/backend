package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQueryTemp;
import com.leesh.inflpick.product.core.domain.Product;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ProductRepository {

    String save(Product product);

    long count();

    @NotNull Product getById(@NotNull String id) throws ProductNotFoundException;

    PageDetails<Collection<Product>> getPage(PageQueryTemp query);

    void deleteById(String id);
}
