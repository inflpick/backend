package com.leesh.inflpick.product.port.out;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException(String id) {
        super(id, "제품");
    }
}
