package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCodeSwaggerDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "인플루언서 API", description = "인플루언서 API 명세서입니다.")
public interface CommandInfluencerControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {CommandInfluencerApiErrorCode.class}, httpMethod = "POST", apiPath = "/influencers")
    @Operation(summary = "인플루언서 생성",
            description = "(관리자 전용) 인플루언서를 생성합니다. 요청 예시에 있는 키워드 id 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 id 값으로 변경 후 요청해주세요.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 생성 요청 정보", required = true, content = @Content(schema = @Schema(implementation = InfluencerWebRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "location", description = "생성된 인플루언서의 URI", schema = @Schema(type = "string")))
            })
    ResponseEntity<Void> create(@RequestBody InfluencerWebRequest request);

    @ApiErrorCodeSwaggerDocs(values = {CommandInfluencerApiErrorCode.class, InfluencerReadApiErrorCode.class}, httpMethod = "PUT", apiPath = "/influencers/{id}")
    @Operation(summary = "인플루언서 수정",
            description = "인플루언서를 수정합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "인플루언서 수정 요청 정보", required = true),
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            }
    )
    ResponseEntity<Void> update(@PathVariable(value = "id")
                                String id,
                                @RequestBody
                                InfluencerWebRequest request);

    @Operation(summary = "인플루언서 삭제",
            description = "인플루언서를 삭제합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)"),
            }
    )
    ResponseEntity<Void> delete(@PathVariable(value = "id")
                                String id);

}
