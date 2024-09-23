package com.leesh.inflpick.product.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface ProductCommandService {

    String create(ProductCommand command);

    void updateProductImage(String id, MultipartFile productImage);
}
