package com.leesh.inflpick.v2.product.application.port.in;

import com.leesh.inflpick.v2.product.application.dto.GetProductResponse;
import com.leesh.inflpick.v2.common.application.dto.PageRequestTemp;
import com.leesh.inflpick.v2.common.application.dto.OffsetPageResponse;

public interface GetProductPageUseCase {

    OffsetPageResponse<GetProductResponse> getPage(PageRequestTemp request);

}
