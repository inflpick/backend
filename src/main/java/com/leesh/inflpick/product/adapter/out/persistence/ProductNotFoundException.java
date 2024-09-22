package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(String uuid) {
        super(uuid, "제품");
    }
}
