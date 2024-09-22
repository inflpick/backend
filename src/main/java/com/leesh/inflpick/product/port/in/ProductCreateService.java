package com.leesh.inflpick.product.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface ProductCreateService {

    String create(ProductCreateCommand command, MultipartFile productImage);
}
