package com.leesh.inflpick.influencer.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class InfluencerNotFoundException extends ResourceNotFoundException {

    public InfluencerNotFoundException(String id) {
        super(id, "인플루언서");
    }
}
