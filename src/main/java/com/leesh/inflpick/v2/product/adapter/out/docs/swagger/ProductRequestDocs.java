package com.leesh.inflpick.v2.product.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.product.application.dto.OnlineStoreRequest;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "제품 등록 요청", description = "제품 등록 요청")
public interface ProductRequestDocs {

    @Schema(description = "제품 이름", example = "핏블리", requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "제품 설명", example = "핏블리", requiredMode = Schema.RequiredMode.REQUIRED)
    String description();

    @ArraySchema(arraySchema = @Schema(description = "제품 키워드 ID 목록",
            example = "[\"6726946b272157735138c837\", \"6726946b272157735138c837\"]",
            defaultValue = "[]",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED),
            maxItems = 10)
    List<String> keywordIds();

    @ArraySchema(arraySchema = @Schema(description = "제품의 온라인 스토어 링크 목록",
            example = "[{\"platform\": \"COUPANG\", \"url\": \"https://coupang.com\"}]",
            implementation = OnlineStoreRequest.class,
            defaultValue = "[]",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED))
    List<OnlineStoreRequest> onlineStoreLinks();
}
