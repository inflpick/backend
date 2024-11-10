package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.product.adapter.out.docs.swagger.CreateProductControllerDocs;
import com.leesh.inflpick.v2.product.application.dto.ProductRequest;
import com.leesh.inflpick.v2.product.application.port.in.CreateProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class CreateProductController implements CreateProductControllerDocs {

    private final CreateProductUseCase createProductUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<Void> create(@RequestBody ProductRequest request) {
        ProductId productId = createProductUseCase.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productId.id())
                .toUri();
        return ResponseEntity.created(location)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

}
