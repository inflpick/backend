package com.leesh.inflpick.product.core.service;

import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.port.in.ProductCommand;
import com.leesh.inflpick.product.port.in.ProductCommandService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.product.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Set;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ProductService implements ProductCommandService, ProductQueryService {

    private final UuidHolder uuidHolder;
    private final KeywordRepository keywordRepository;
    private final StorageService storageService;
    private final ProductRepository productRepository;

    @Override
    public String create(ProductCommand command) {
        Set<String> keywordIds = command.keywordUuids();
        Keywords keywords = keywordRepository.getAllByIds(keywordIds);
        Product product = command.toEntity(uuidHolder);
        product.addKeywords(keywords);
        return productRepository.save(product);
    }

    @Override
    public void updateProductImage(String id, MultipartFile productImage) {
        Product product = productRepository.getById(id);
        Path basePath = product.getProductImageBasePath();
        String uploadPath = storageService.upload(productImage, basePath);
        product.registerProductImagePath(uploadPath);
        productRepository.save(product);
    }

    @Override
    public Product getById(String id) throws ProductNotFoundException {
        return productRepository.getById(id);
    }
}
