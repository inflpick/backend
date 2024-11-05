package com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant.CommonInfluencerApiErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "인플루언서 API", description = "인플루언서 API")
public interface DeleteInfluencerControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {CommonInfluencerApiErrorCode.class}, httpMethod = "DELETE", apiPath = "/influencer/{influencerId}")
    @Operation(summary = "인플루언서 삭제하기",
            description = "인플루언서를 삭제합니다.",
    security = {
            @SecurityRequirement(name = "Bearer-Auth")
    },
    parameters = {
            @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "인플루언서 ID"),
    },
    responses = {
            @ApiResponse(responseCode = "204", description = "인플루언서 삭제 성공 (본문 없음)")
    })
    ResponseEntity<Void> delete(String influencerId);
}
