package com.leesh.inflpick.product.adapter.out.docs.swagger;

import com.leesh.inflpick.common.adapter.out.docs.swagger.ApiErrorCodeSwaggerDocs;
import com.leesh.inflpick.product.adapter.in.web.exception.NotSupportOnlineStorePlatformException;
import com.leesh.inflpick.product.application.exception.InvalidProductImageFormat;
import com.leesh.inflpick.product.application.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "제품 API", description = "제품 API")
public interface UpdateProductImageControllerDocs {

    @ApiErrorCodeSwaggerDocs(values = {ProductNotFoundException.class, NotSupportOnlineStorePlatformException.class, InvalidProductImageFormat.class}, httpMethod = "PATCH", apiPath = "/products/{id}/image")
    @Operation(summary = "제품 이미지 수정",
            description = "제품의 이미지를 수정합니다.",
            security = {
                    @SecurityRequirement(name = "Bearer-Auth")
            },
            parameters = {
                    @Parameter(name = "id", schema = @Schema(type = "string"), required = true, example = "6728d0aad5e37a27ed7e2907", description = "제품 ID"),
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "성공 (본문 없음)")
            }
    )
    ResponseEntity<Void> updateProductImage(String id,
                                            @Parameter(description = "제품 이미지 파일", required = true)
                                            MultipartFile image);
}
