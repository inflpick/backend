package com.leesh.inflpick.v2.product.application.service;

import com.leesh.inflpick.v2.common.application.dto.PageRequestTemp;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;
import com.leesh.inflpick.v2.common.application.port.out.storage.StoragePort;
import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.product.application.port.in.GetProductPageUseCase;
import com.leesh.inflpick.v2.product.application.port.out.QueryProductPort;
import com.leesh.inflpick.v2.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class GetProductPageService implements GetProductPageUseCase {

    private final QueryProductPort queryProductPort;
    private final StoragePort storagePort;

    @Override
    public OffsetPageResponse<GetProductResponse> getPage(PageRequestTemp request) {
        OffsetPageResponse<Product> offsetPageResponse = queryProductPort.query(request);
        List<GetProductResponse> productResponses = offsetPageResponse.contents().stream().map(product -> {
            String profileImageUrl = storagePort.getUrlString(product.image().path());
            return GetProductResponse.from(product, profileImageUrl);
        }).toList();
        return new OffsetPageResponse<>(productResponses,
                offsetPageResponse.currentPage(),
                offsetPageResponse.totalPages(),
                offsetPageResponse.size(),
                offsetPageResponse.totalElements(),
                offsetPageResponse.sortProperties());
    }
}
