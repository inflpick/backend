package com.leesh.inflpick.v2.product.adapter.in.web.controller;

import com.leesh.inflpick.v2.common.application.dto.WebOffsetPageRequestTemp;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.product.application.port.in.GetProductPageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GetProductPageController {

    private final GetProductPageUseCase getProductPageUseCase;

    @GetMapping("/products")
    public ResponseEntity<OffsetPageResponse<GetProductResponse>> getProductPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                                 @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                                 @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort) {
        WebOffsetPageRequestTemp webOffsetPageRequest = new WebOffsetPageRequestTemp(page, size, sort);
        OffsetPageResponse<GetProductResponse> response = getProductPageUseCase.getPage(webOffsetPageRequest);
        return ResponseEntity.ok(response);
    }

}
