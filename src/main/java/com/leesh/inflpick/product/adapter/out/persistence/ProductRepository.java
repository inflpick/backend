package com.leesh.inflpick.product.adapter.out.persistence;

import com.leesh.inflpick.common.adapter.out.persistence.SpringDataPageRequestConverter;
import com.leesh.inflpick.common.application.dto.PageRequest;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.common.application.dto.Sortable;
import com.leesh.inflpick.keyword.adapter.out.persistence.mongo.KeywordMongoRepository;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductDocument;
import com.leesh.inflpick.product.adapter.out.persistence.mongo.ProductMongoRepository;
import com.leesh.inflpick.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.product.domain.Product;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Repository
public class ProductRepository implements CommandProductPort, QueryProductPort {

    private final ProductMongoRepository productMongoRepository;
    private final KeywordMongoRepository keywordMongoRepository;

    @Override
    public ProductId save(Product product) {
        ProductDocument document = ProductDocument.from(product);
        String id = productMongoRepository.save(document).id();
        return ProductId.create(id);
    }

    @Override
    public void delete(ProductId id) {
        productMongoRepository.deleteById(id.id());
    }

    @Override
    public Optional<Product> query(ProductId productId) {
        return productMongoRepository.findById(productId.id())
                .map(ProductDocument::toEntity);
    }

    @Override
    public PageResponse<Product> query(PageRequest request) {
        Sortable sortable = () -> Arrays.stream(ProductSortable.values())
                .map(ProductSortable::name)
                .toList();
        org.springframework.data.domain.PageRequest pageRequest = SpringDataPageRequestConverter.convert(request, sortable);
        Page<ProductDocument> documentPage = productMongoRepository.findAll(pageRequest);
        List<Product> products = documentPage
                .map(ProductDocument::toEntity)
                .stream()
                .toList();
        return PageResponse.create(products,
                documentPage.getNumber(),
                documentPage.getTotalPages(),
                documentPage.getSize(),
                documentPage.getTotalElements(),
                documentPage.getSort().toString());
    }
}
