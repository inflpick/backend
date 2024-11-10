package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.common.application.dto.PageRequest;
import com.leesh.inflpick.v2.common.application.dto.PageResponse;
import com.leesh.inflpick.v2.product.adapter.out.docs.swagger.GetProductControllerDocs;
import com.leesh.inflpick.v2.product.application.dto.ProductResponse;
import com.leesh.inflpick.v2.product.application.port.in.GetProductUseCase;
import com.leesh.inflpick.v2.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetProductController implements GetProductControllerDocs {

    private final GetProductUseCase getProductUseCase;

    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<ProductResponse> get(@PathVariable(value = "id") String id) {
        ProductId productId = ProductId.create(id);
        ProductResponse response = getProductUseCase.get(productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<PageResponse<ProductResponse>> getPage(@RequestParam(name = "page", required = false, defaultValue = "0")                                                                             Integer page,
                                                                 @RequestParam(name = "size", required = false, defaultValue = "20")
                                                                       Integer size,
                                                                 @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc")
                                                                           String[] sort) {
        PageRequest request = PageRequest.create(page, size, sort);
        PageResponse<ProductResponse> productPage = getProductUseCase.getPage(request);
        return ResponseEntity.ok().body(productPage);
    }

}
