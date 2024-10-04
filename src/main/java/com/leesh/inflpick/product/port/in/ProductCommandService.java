package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.product.port.ProductCommand;
import org.springframework.web.multipart.MultipartFile;

public interface ProductCommandService {

    String create(ProductCommand command);

    void updateProductImage(String id, MultipartFile productImage);

    void delete(String id);

    void update(String id, ProductCommand command);
}
