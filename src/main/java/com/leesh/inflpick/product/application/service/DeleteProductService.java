package com.leesh.inflpick.product.application.service;

import com.leesh.inflpick.product.application.port.in.DeleteProductUseCase;
import com.leesh.inflpick.product.application.port.out.CommandProductPort;
import com.leesh.inflpick.product.domain.vo.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeleteProductService implements DeleteProductUseCase {

    private final CommandProductPort commandProductPort;

    @Override
    public void delete(ProductId id) {
        commandProductPort.delete(id);
    }
}
