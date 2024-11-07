package com.leesh.inflpick.v2.product.adapter.in.web;

import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.product.application.port.in.GetProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetProductController {

    private final GetProductUseCase getProductUseCase;

    @GetMapping("/products/{id}")
    public ResponseEntity<GetProductResponse> get(@PathVariable String id) {
        ProductId productId = ProductId.create(id);
        GetProductResponse response = getProductUseCase.get(productId);
        return ResponseEntity.ok(response);
    }

}
