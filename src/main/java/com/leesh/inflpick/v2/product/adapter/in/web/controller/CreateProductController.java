package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.product.adapter.in.web.dto.UpdateProductWebRequest;
import com.leesh.inflpick.v2.product.application.dto.CreateProductRequest;
import com.leesh.inflpick.v2.product.application.dto.UpdateProductRequest;
import com.leesh.inflpick.v2.product.application.port.in.CreateProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CreateProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody UpdateProductWebRequest webRequest) {
        UpdateProductRequest request1 = webRequest.toRequest();
        ProductId productId = createProductUseCase.create(null);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productId)
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
