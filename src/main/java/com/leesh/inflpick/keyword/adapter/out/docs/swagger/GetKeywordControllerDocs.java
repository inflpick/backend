package com.leesh.inflpick.keyword.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.application.dto.PageResponse;
import com.leesh.inflpick.keyword.application.dto.KeywordResponse;
import com.leesh.inflpick.keyword.application.exception.AlreadyExistKeywordNameException;
import com.leesh.inflpick.keyword.application.exception.KeywordNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "키워드 API", description = "키워드 정보를 조회합니다.")
public interface GetKeywordControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {AlreadyExistKeywordNameException.class, KeywordNotFoundException.class}, httpMethod = "GET", apiPath = "/keywords")
    @Operation(summary = "키워드 단건 조회",
            description = "키워드를 조회합니다.",
            parameters = {
                    @Parameter(name = "id", description = "키워드 ID", required = true, example = "6726946b272157735138c837", schema = @Schema(implementation = String.class))
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = KeywordResponse.class)))
            })
    ResponseEntity<KeywordResponse> get(String id);

    @Operation(summary = "키워드 목록 페이지 조회",
            description = "키워드 목록 페이지를 조회합니다.",
            parameters = {
                    @Parameter(name = "page", description = "페이지 번호 (기본값: 0)", example = "0", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "size", description = "한 페이지 크기 (기본값: 20)", example = "20", schema = @Schema(implementation = Integer.class)),
                    @Parameter(name = "sort", description = "정렬 기준 (기본값: createdDate,asc), 정렬 기준이 올바르지 않은 값을 입력한 경우, 기본 값으로 동작", example = "createdDate,asc",
                            examples = {
                                    @ExampleObject(name = "createdDate,asc", value = "createdDate,asc", description = "생성일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "createdDate,desc", value = "createdDate,desc", description = "생성일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,asc", value = "lastModifiedDate,asc", description = "수정일 기준 오름차순 정렬"),
                                    @ExampleObject(name = "lastModifiedDate,desc", value = "lastModifiedDate,desc", description = "수정일 기준 내림차순 정렬"),
                                    @ExampleObject(name = "name,asc", value = "name,asc", description = "키워드 이름 기준 오름차순 정렬"),
                                    @ExampleObject(name = "name,desc", value = "name,desc", description = "키워드 이름 기준 내림차순 정렬")
                            })
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = PageResponse.class)))
            })
    ResponseEntity<PageResponse<KeywordResponse>> getPage(Integer page, Integer size, String[] sort);
}
