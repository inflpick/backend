package com.leesh.inflpick.product.adapter.in.web.controller;


import com.leesh.inflpick.common.application.service.FileTypeValidator;
import com.leesh.inflpick.product.adapter.out.docs.swagger.UpdateProductImageControllerDocs;
import com.leesh.inflpick.product.application.port.in.UpdateProductImageUseCase;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class UpdateProductImageController implements UpdateProductImageControllerDocs {

    private final UpdateProductImageUseCase updateProductImageUseCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping(value = "/products/{id}/image", consumes = "multipart/form-data")
    public ResponseEntity<Void> updateProductImage(@PathVariable(value = "id") String id,
                                                   @RequestPart(value = "image") MultipartFile image) {
        FileTypeValidator.validateImageFile(image);
        ProductId productId = ProductId.create(id);
        updateProductImageUseCase.updateProductImage(productId, image);
        return ResponseEntity.noContent().build();
    }
}
