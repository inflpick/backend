package com.leesh.inflpick.common.adapter.in.web.docs;

import com.leesh.inflpick.common.adapter.in.web.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.common.adapter.in.web.value.ApiErrorResponse;
import com.leesh.inflpick.common.adapter.in.web.value.CommonApiErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "공통 API", description = "공통 API")
public interface CommonApiDocs {

    @ApiErrorCodeSwaggerDocs(values = {CommonApiErrorCode.class}, httpMethod = "*", apiPath = "/api/**")
    @Operation(summary = "공통 API 에러 응답",
            description = "공통 API 에러 응답을 명시합니다.")
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    ResponseEntity<Void> common();
}
