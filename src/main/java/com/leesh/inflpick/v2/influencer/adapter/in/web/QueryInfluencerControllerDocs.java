package com.leesh.inflpick.v2.influencer.adapter.in.web;

import com.leesh.inflpick.common.adapter.in.web.value.WebPageResponse;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerGetListsApiErrorCode;
import com.leesh.inflpick.influencer.adapter.in.web.value.InfluencerReadApiErrorCode;
import com.leesh.inflpick.v2.shared.adapter.in.web.ApiErrorCodeSwaggerDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface QueryInfluencerControllerDocs {


    @ApiErrorCodeSwaggerDocs(values = {InfluencerReadApiErrorCode.class}, httpMethod = "GET", apiPath = "/influencers/{id}")
    @Operation(summary = "인플루언서 단건 조회", description = "인플루언서를 단건 조회합니다.",
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "f103314b-778c-49fc-ae9c-7956794a3bdf", description = "인플루언서 ID")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = InfluencerWebResponse.class))),
            }
    )
    ResponseEntity<InfluencerWebResponse> get(@PathVariable String id);

    @ApiErrorCodeSwaggerDocs(values = {InfluencerGetListsApiErrorCode.class}, httpMethod = "GET", apiPath = "/influencers?page={page}&size={size}&sort={sortType,sortDirection}")
    @Operation(summary = "인플루언서 목록 조회",
            description = "인플루언서 목록을 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc, 다중 정렬 가능), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "인플루언서 이름 기준 오름차순 정렬")
                            })
            })
    ResponseEntity<WebPageResponse<InfluencerWebResponse>> list(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                @RequestParam(name = "size", required = false, defaultValue = "20") Integer size,
                                                                @RequestParam(name = "sort", required = false, defaultValue = "createdDate,asc") String[] sort);
}
