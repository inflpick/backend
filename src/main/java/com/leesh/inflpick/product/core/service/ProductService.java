package com.leesh.inflpick.product.core.service;

import com.leesh.inflpick.common.port.out.UuidHolder;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.influencer.port.out.StorageService;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.port.in.ProductCreateCommand;
import com.leesh.inflpick.product.port.in.ProductCreateService;
import com.leesh.inflpick.product.port.in.ProductReadService;
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
public class ProductService implements ProductCreateService, ProductReadService {

    private final UuidHolder uuidHolder;
    private final KeywordRepository keywordRepository;
    private final StorageService storageService;
    private final ProductRepository productRepository;

    @Override
    public String create(ProductCreateCommand command, MultipartFile productImage) {

        Set<String> keywordIds = command.keywordUuids();
        Keywords keywords = keywordRepository.getAllByUuids(keywordIds);

        Product product = command.toEntity(uuidHolder);
        product.addKeywords(keywords);

        // 제품 이미지 업로드
        Path basePath = product.getProductImageBasePath();
        String uploadPath = storageService.upload(productImage, basePath);
        product.registerProductImage(uploadPath);

        return productRepository.save(product);
    }

    @Override
    public Product getByUuid(String uuid) {
        return productRepository.getByUuid(uuid);
    }
}
