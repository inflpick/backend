package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProductImageUseCase {

    void updateProductImage(ProductId id, MultipartFile image);
}
