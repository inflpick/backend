package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductMongoRepository;
import com.leesh.inflpick.product.core.Product;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.product.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Set;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductMongoRepository productMongoRepository;
    private final KeywordRepository keywordRepository;

    @Override
    public String save(@NotNull Product product) {
        ProductDocument document = ProductDocument.from(product);
        productMongoRepository.save(document);
        return document.getId();
    }

    @Override
    public long count() {
        return productMongoRepository.count();
    }

    @Override
    public @NotNull Product getById(@NotNull String id) throws ProductNotFoundException {
        ProductDocument productDocument = productMongoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        Set<String> keywordUuids = productDocument.getKeywordUuids();
        Keywords keywords = keywordRepository.getAllByIds(keywordUuids);

        return productDocument.toEntity(keywords);
    }
}
