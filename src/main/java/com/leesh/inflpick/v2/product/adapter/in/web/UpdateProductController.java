package com.leesh.inflpick.v2.product.adapter.in.web;

import com.leesh.inflpick.v2.product.adapter.in.web.dto.UpdateProductWebRequest;
import com.leesh.inflpick.v2.product.application.dto.UpdateProductRequest;
import com.leesh.inflpick.v2.product.application.port.in.UpdateProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UpdateProductController {

    private final UpdateProductUseCase updateProductUseCase;

    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable String id,
                                              @RequestBody UpdateProductWebRequest webRequest) {
        ProductId productId = ProductId.create(id);
        UpdateProductRequest request = webRequest.toRequest();
        updateProductUseCase.update(productId, request);
        return ResponseEntity.noContent().build();
    }

}
