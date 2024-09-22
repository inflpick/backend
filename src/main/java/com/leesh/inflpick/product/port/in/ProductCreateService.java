package com.leesh.inflpick.product.port.in;

import com.leesh.inflpick.product.core.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductCreateService {

    String create(ProductCreateCommand command, MultipartFile productImage);

    Product getByUuid(String uuid);
}
