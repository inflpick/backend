package com.leesh.inflpick.product.application.port.in;

import com.leesh.inflpick.product.domain.vo.ProductId;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateProductImageUseCase {

    void updateProductImage(ProductId id, MultipartFile image);
}
