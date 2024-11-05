package com.leesh.inflpick.v2.influencer.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.influencer.adapter.in.web.controller.constant.CreateInfluencerApiErrorCode;
import com.leesh.inflpick.v2.influencer.application.dto.InfluencerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(name = "인플루언서 API", description = "인플루언서 API")
public interface CreateInfluencerControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {CreateInfluencerApiErrorCode.class}, httpMethod = "POST", apiPath = "/influencer")
    @Operation(summary = "인플루언서 등록하기",
            description = "인플루언서를 등록합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InfluencerRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "인플루언서 등록 성공", headers = {
                            @Header(name = "Location", description = "생성된 인플루언서 ID", schema = @Schema(type = "string", implementation = String.class))
                    })}
    )
    ResponseEntity<Void> create(InfluencerRequest request);


}
