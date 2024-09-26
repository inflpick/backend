package com.leesh.inflpick.review.port.in;

import com.leesh.inflpick.influencer.core.domain.Influencer;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.review.port.ReviewCommand;

public interface ReviewCommandService {

    String create(Influencer reviewer, Product product, ReviewCommand command);

}
