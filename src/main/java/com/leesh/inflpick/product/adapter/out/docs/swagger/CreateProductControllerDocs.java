package com.leesh.inflpick.product.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.product.application.dto.ProductRequest;
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

@Tag(name = "제품 API", description = "제품 API")
public interface CreateProductControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {NotSupportOnlineStorePlatformException.class}, httpMethod = "POST", apiPath = "/products")
    @Operation(summary = "제품 등록하기",
            description = "제품을 등록합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            requestBody = @RequestBody(
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductRequest.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "제품 등록 성공", headers = {
                            @Header(name = "Location", description = "생성된 제품 ID", schema = @Schema(type = "string", implementation = String.class))
                    })}
    )
    ResponseEntity<Void> create(ProductRequest request);
}
