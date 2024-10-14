package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.common.port.PageResponse;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductMongoRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductPageResponse;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductSortProperty;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.product.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
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

        Set<String> keywordUuids = productDocument.getKeywordIds();
        Keywords keywords = keywordRepository.getAllByIds(keywordUuids);

        return productDocument.toEntity(keywords);
    }

    @Override
    public PageResponse<Product> getPage(com.leesh.inflpick.common.port.PageRequest request) {

        PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, () -> Arrays.stream(ProductSortProperty.values())
                .map(ProductSortProperty::getValue)
                .toList());
        Page<ProductDocument> documentPage = productMongoRepository.findAll(pageRequest);
        List<ProductDocument> content = documentPage.getContent();
        List<Product> entities = convertToEntities(content);
        String sortProperties = documentPage.getSort().toString();
        return ProductPageResponse.builder()
                .contents(entities)
                .currentPage(documentPage.getNumber())
                .totalPages(documentPage.getTotalPages())
                .size(documentPage.getSize())
                .totalElements(documentPage.getTotalElements())
                .sortProperties(sortProperties)
                .build();
    }

    @Override
    public void deleteById(String id) {
        productMongoRepository.deleteById(id);
    }

    private static String @NotNull [] getSortProperties(Sort sort) {
        return sort.isSorted() ?
                sort.toString().split(",") :
                Sort.unsorted().toString().split(",");
    }

    private @NotNull List<Product> convertToEntities(List<ProductDocument> content) {
        return content.stream().map(document -> {
            Set<String> keywordIds = document.getKeywordIds();
            Keywords keywords = keywordRepository.getAllByIds(keywordIds);
            return document.toEntity(keywords);
        }).toList();
    }
}
