package com.leesh.inflpick.product.adapter.in.web.controller;

import com.leesh.inflpick.product.adapter.out.docs.swagger.UpdateProductControllerDocs;
import com.leesh.inflpick.product.application.dto.ProductRequest;
import com.leesh.inflpick.product.application.port.in.UpdateProductUseCase;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UpdateProductController implements UpdateProductControllerDocs {

    private final UpdateProductUseCase updateProductUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/products/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable(value = "id") String id,
                                              @RequestBody ProductRequest request) {
        ProductId productId = ProductId.create(id);
        updateProductUseCase.update(productId, request);
        return ResponseEntity.noContent().build();
    }

}
