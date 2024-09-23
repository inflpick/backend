package com.leesh.inflpick.review.port.out;

import com.leesh.inflpick.common.adapter.out.persistence.ResourceNotFoundException;

public class ReviewNotFoundException extends ResourceNotFoundException {
    public ReviewNotFoundException(String id) {
        super(id, "리뷰");
    }
}
