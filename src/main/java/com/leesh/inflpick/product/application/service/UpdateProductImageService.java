package com.leesh.inflpick.product.application.service;

import com.leesh.inflpick.common.application.exception.FileFormatException;
import com.leesh.inflpick.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.product.application.exception.InvalidProductImageFormat;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import com.leesh.inflpick.product.application.port.in.UpdateProductImageUseCase;
import com.leesh.inflpick.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.nio.file.Path;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateProductImageService implements UpdateProductImageUseCase {

    private final StoragePort storagePort;
    private final QueryProductPort queryProductPort;
    private final CommandProductPort commandProductPort;

    @Override
    public void updateProductImage(ProductId id, MultipartFile image) {
        Product product = queryProductPort.query(id).orElseThrow(() -> new ProductNotFoundException(id));
        Path basePath = product.image().getBasePath(id);
        URL uploadUrl = uploadProductImage(image, basePath);
        String imagePath = uploadUrl.getPath();
        Product updatedProduct = product.updateImage(imagePath);
        commandProductPort.save(updatedProduct);
    }

    private URL uploadProductImage(MultipartFile profileImage, Path basePath) {
        URL uploadUrl;
        try {
            uploadUrl = storagePort.upload(profileImage, basePath);
        } catch (FileFormatException e) {
            throw new InvalidProductImageFormat(profileImage.getOriginalFilename());
        }
        return uploadUrl;
    }
}
