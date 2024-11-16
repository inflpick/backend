package com.leesh.inflpick.product.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "제품 API", description = "제품 API")
public interface DeleteProductControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {ProductNotFoundException.class}, httpMethod = "DELETE", apiPath = "/products/{id}")
    @Operation(summary = "제품 삭제하기",
            description = "제품을 삭제합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "제품 ID"),
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "제품 삭제 성공 (본문 없음)")
            })
    ResponseEntity<Void> delete(String id);
}
