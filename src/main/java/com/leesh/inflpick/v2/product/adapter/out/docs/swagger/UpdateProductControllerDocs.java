package com.leesh.inflpick.v2.product.adapter.out.docs.swagger;

import com.leesh.inflpick.v2.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.v2.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.v2.product.application.dto.ProductRequest;
import com.leesh.inflpick.v2.product.application.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "제품 API", description = "제품 API")
public interface UpdateProductControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {ProductNotFoundException.class, NotSupportOnlineStorePlatformException.class}, httpMethod = "PUT", apiPath = "/products/{id}")
    @Operation(summary = "제품 수정하기",
            description = "제품을 수정합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6726946b272157735138c837", description = "제품 ID"),
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductRequest.class))
            ),
            responses = {
                @ApiResponse(responseCode = "204", description = "제품 수정 성공")
            }
    )
    ResponseEntity<Void> updateProduct(String id, ProductRequest request);

}
