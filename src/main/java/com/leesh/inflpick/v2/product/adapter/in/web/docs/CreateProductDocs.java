package com.leesh.inflpick.v2.product.adapter.in.web.docs;

import com.leesh.inflpick.v2.product.adapter.in.web.dto.CreateProductApiErrorCode;
import com.leesh.inflpick.v2.product.adapter.in.web.dto.CreateProductWebRequest;
import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "제품 API", description = "제품 API 명세서")
public interface CreateProductDocs {

    @ApiErrorCodeSwaggerDocs(values = {CreateProductApiErrorCode.class}, httpMethod = "POST", apiPath = "/products")
    @Operation(summary = "제품 생성", description = "제품을 생성합니다. 요청 예시에 있는 키워드 ID 값은 실제 존재하는 값이 아니므로, 키워드 등록 후 실제 ID 값으로 변경 후 요청해주세요.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @RequestBody(description = "제품 생성 요청 정보", required = true, content = @Content(schema = @Schema(implementation = CreateProductWebRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "성공", headers = {
                            @Header(name = "Location", description = "생성된 제품 ID", schema = @Schema(implementation = String.class))
                    }),
            })
    ResponseEntity<Void> create(CreateProductWebRequest request);

}
