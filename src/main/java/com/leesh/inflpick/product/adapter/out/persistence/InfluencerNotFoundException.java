package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class InfluencerNotFoundException extends ResourceNotFoundException {

    public InfluencerNotFoundException(String uuid) {
        super(uuid, "인플루언서");
    }
}
