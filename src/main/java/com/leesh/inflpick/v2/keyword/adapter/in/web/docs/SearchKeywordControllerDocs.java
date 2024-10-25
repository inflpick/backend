package com.leesh.inflpick.v2.keyword.adapter.in.web.docs;

import com.leesh.inflpick.v2.keyword.adapter.in.web.dto.KeywordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "키워드 API", description = "키워드 API 명세서입니다.")
public interface SearchKeywordControllerDocs {

    @Operation(summary = "키워드 명으로 검색", description = "입력한 키워드 명과 \"유사한\" 키워드를 검색합니다.",
            parameters = {
                    @Parameter(name = "name", description = "키워드 명", required = true, example = "기술")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공")
            })
    ResponseEntity<List<KeywordResponse>> search(@RequestParam(value = "name") String name);

}
