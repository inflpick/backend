package com.leesh.inflpick.common.adapter.out.docs.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "커서 응답", description = "커서 응답")
public interface CursorResponseDocs {

    @Schema(description = "한 페이지 크기")
    Integer limit();

    @Schema(description = "컨텐츠")
    Object contents();

    @Schema(description = "다음 페이지 존재 여부")
    Boolean hasNext();
}
