package com.leesh.inflpick.product.adapter.in.web.docs;

import com.leesh.inflpick.product.adapter.in.web.value.OnlineStoreLinkRequest;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "제품 생성 요청", description = "제품을 생성 요청 필드")
public interface ProductRequestApiDocs {

    @Schema(description = "제품명", example = "마이프로틴 “6레이어 프로틴바” (육겹바)", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String name();

    @Schema(description = "제품 소개 글", example = "프로틴 스낵 베스트셀러 중 하나로 6겹의 뛰어난 맛과 식감을 자랑합니다.\n 1개당 20g 이상의 단백질과 탄수화물뿐만 아니라 우리 몸에 필요한 식이섬유를 풍부하게 함유하였습니다. 6 레이어 프로틴바로 맛과 영양을 모두 챙기세요!", implementation = String.class, requiredMode = Schema.RequiredMode.REQUIRED)
    String description();

    @ArraySchema(arraySchema = @Schema(description = "제품에 등록할 키워드 UUID 목록", example = "[\"92624c72-1cf2-4762-8c45-fe1a1f0a3e97\", \"8eabcaa9-5c70-46a5-a7c0-b580b1d20316\"]", implementation = String.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
    List<String> keywordUuids();

    @ArraySchema(arraySchema = @Schema(description = "제품의 온라인 스토어 링크 목록", implementation = OnlineStoreLinkRequest.class, requiredMode = Schema.RequiredMode.NOT_REQUIRED))
    List<OnlineStoreLinkRequest> onlineStoreLinks();

}
