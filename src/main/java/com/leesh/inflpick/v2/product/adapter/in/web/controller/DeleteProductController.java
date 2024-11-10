package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.product.adapter.out.docs.swagger.DeleteProductControllerDocs;
import com.leesh.inflpick.v2.product.application.port.in.DeleteProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DeleteProductController implements DeleteProductControllerDocs {

    private final DeleteProductUseCase deleteProductUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") String id) {
        ProductId productId = ProductId.create(id);
        deleteProductUseCase.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
