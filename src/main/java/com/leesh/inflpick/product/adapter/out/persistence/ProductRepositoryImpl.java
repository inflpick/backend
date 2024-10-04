package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.common.core.Direction;
import com.leesh.inflpick.common.port.PageDetails;
import com.leesh.inflpick.common.port.PageQuery;
import com.leesh.inflpick.influencer.core.domain.value.Keywords;
import com.leesh.inflpick.keyword.port.out.KeywordRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductMongoRepository;
import com.leesh.inflpick.product.core.domain.Product;
import com.leesh.inflpick.product.port.ProductSortType;
import com.leesh.inflpick.product.port.out.ProductNotFoundException;
import com.leesh.inflpick.product.port.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
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
    public PageDetails<Collection<Product>> getPage(PageQuery<ProductSortType> query) {

        Sort sortCriteria = getSortCriteria(query.sortPairs());

        PageRequest pageRequest = PageRequest.of(query.page(),
                query.size(),
                sortCriteria);

        Page<ProductDocument> documentPage = productMongoRepository.findAll(pageRequest);
        List<ProductDocument> content = documentPage.getContent();
        Set<String> allKeywordIds = getAllKeywordIds(content);
        List<Product> contents = convertToEntities(allKeywordIds, content);

        return new PageDetails<>(
                documentPage.getNumber(),
                documentPage.getSize(),
                documentPage.getTotalPages(),
                documentPage.getTotalElements(),
                getSortProperties(documentPage.getSort()),
                contents);
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

    private Sort getSortCriteria(Collection<Pair<ProductSortType, Direction>> sortPairs) {
        Sort sortOrder = Sort.unsorted();
        for (Pair<ProductSortType, Direction> sortPair : sortPairs) {
            ProductSortType sortType = sortPair.getFirst();
            String sortField = sortType.getValue();
            Direction direction = sortPair.getSecond();
            Sort.Direction sortDirection = direction.isDescending() ? Sort.Direction.DESC : Sort.Direction.ASC;
            sortOrder = sortOrder.and(Sort.by(sortDirection, sortField));
        }
        return sortOrder;
    }

    private @NotNull List<Product> convertToEntities(Set<String> allKeywordIds, List<ProductDocument> content) {
        Keywords allKeywords = keywordRepository.getAllByIds(allKeywordIds);
        return content.stream().map(productDocument -> {
            Set<String> keywordIds = productDocument.getKeywordIds();
            Keywords keywords = allKeywords.subset(keywordIds);
            return productDocument.toEntity(keywords);
        }).toList();
    }

    private static @NotNull Set<String> getAllKeywordIds(List<ProductDocument> content) {
        Set<String> allKeywordIds = new HashSet<>();
        content.forEach(productDocument -> allKeywordIds.addAll(productDocument.getKeywordIds()));
        return allKeywordIds;
    }
}
