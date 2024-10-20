package com.leesh.inflpick.product.core.service;

import com.leesh.inflpick.common.port.PageRequest;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.common.port.out.StorageService;
import com.leesh.inflpick.v2.adapter.out.uuid.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductCommand;
import com.leesh.inflpick.product.port.in.ProductCommandService;
import com.leesh.inflpick.product.port.in.ProductQueryService;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.product.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
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
        URL uploadUrl = storageService.upload(productImage, basePath);
        product.updateProductImagePath(uploadUrl.getPath());
        productRepository.save(product);
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(String id, ProductCommand command) {
        Product product = productRepository.getById(id);
        Set<String> keywordIds = command.keywordUuids();
        Keywords keywords = keywordRepository.getAllByIds(keywordIds);
        product.update(command, keywords);
        productRepository.save(product);
    }

    @Override
    public Product query(String id) throws ProductNotFoundException {
        return productRepository.getById(id);
    }

    @Override
    public PageResponse<Product> query(PageRequest request) {
        return productRepository.getPage(request);
    }
}
