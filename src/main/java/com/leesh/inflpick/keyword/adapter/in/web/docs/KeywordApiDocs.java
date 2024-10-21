package com.leesh.inflpick.keyword.adapter.in.web.docs;

import com.leesh.inflpick.v2.adapter.out.docs.swagger.common.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordCreateApiErrorCode;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordRequest;
import com.leesh.inflpick.keyword.adapter.in.web.value.KeywordResponse;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "키워드 API", description = "키워드 API 명세서입니다.")
public interface KeywordApiDocs {

    @ApiErrorCodeSwaggerDocs(values = KeywordCreateApiErrorCode.class, httpMethod = "POST", apiPath = "/keywords")
    @Operation(summary = "키워드 생성", description = "키워드를 생성합니다.",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = KeywordRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = @Header(name = "Location", description = "생성된 리소스의 URI", schema = @Schema(type = "string")))
            })
    ResponseEntity<Void> create(@RequestBody KeywordRequest request);

    @Operation(summary = "키워드 명으로 검색", description = "입력한 키워드 명과 \"유사한\" 키워드를 검색합니다.",
            parameters = {
                    @Parameter(name = "name", description = "키워드 명", required = true, example = "기술")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공")
            })
    ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name") String name);

    @ApiErrorCodeSwaggerDocs(values = KeywordCreateApiErrorCode.class, httpMethod = "PUT", apiPath = "/keywords")
    @Operation(summary = "키워드 수정", description = "키워드 수정",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            parameters = {@Parameter(name = "name", description = "키워드 이름", required = true, example = "기술")},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(schema = @Schema(implementation = KeywordRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
            }
    )
    ResponseEntity<Void> update(@PathVariable String name,
                                       @RequestBody KeywordRequest request);

    @Operation(summary = "키워드 삭제", description = "키워드 삭제",
            security = {@SecurityRequirement(name = "Bearer-Auth")},
            parameters = {@Parameter(name = "name", description = "키워드 이름", required = true, example = "기술")},
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
            }
    )
    ResponseEntity<Void> delete(@PathVariable String name);
}
